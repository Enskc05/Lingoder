package com.lingoder.v1.service;

import com.lingoder.v1.dto.PasswordResetRequestDto;
import com.lingoder.v1.dto.SignInRequestDto;
import com.lingoder.v1.dto.SignInResponseDto;
import com.lingoder.v1.dto.SignUpRequestDto;
import com.lingoder.v1.handler.UserAlreadyExistsException;
import com.lingoder.v1.helper.CodeGenerator;
import com.lingoder.v1.model.PasswordResetCode;
import com.lingoder.v1.model.User;
import com.lingoder.v1.repository.PasswordResetCodeRepository;
import com.lingoder.v1.repository.UserRepository;
import com.lingoder.v1.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoderConfig;
    private final UserDetailsService userDetailsService;
    private final JwtService token;
    private final PasswordResetCodeService passwordResetCodeService;
    private final EmailService emailService;




    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoderConfig, UserDetailsService userDetailsService, JwtService token, PasswordResetCodeRepository codeRepository, PasswordResetCodeService passwordResetCodeService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.userDetailsService = userDetailsService;
        this.token = token;
        this.passwordResetCodeService = passwordResetCodeService;
        this.emailService = emailService;
    }

    public User signUp(SignUpRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + request.getUsername() + "' is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email '" + request.getEmail() + "' is already registered");
        }

        try {
            User user = new User(
                    UUID.randomUUID(),
                    request.getUsername(),
                    request.getEmail(),
                    passwordEncoderConfig.encode(request.getPassword()),
                    request.getRole(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    null
            );

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("Username or email is already in use");
        }
    }
    public SignInResponseDto signIn(SignInRequestDto request){
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwt = token.generateToken(userDetails);
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

        return new SignInResponseDto(jwt);
    }

    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-posta '" + email + "' bulunamadı"));

        passwordResetCodeService.deleteByUser(user);

        String code = CodeGenerator.generateCode();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(15);

        PasswordResetCode resetCode = new PasswordResetCode(code, user, expiryDate);

        passwordResetCodeService.save(resetCode);

        try {
            emailService.sendPasswordResetCode(user.getEmail(), code);
        } catch (Exception e) {
            throw new RuntimeException("Şifre sıfırlama kodu gönderilemedi: " + e.getMessage());
        }
    }

    @Transactional
    public void resetPassword(PasswordResetRequestDto request) {
        PasswordResetCode resetCode = passwordResetCodeService.findByCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Geçersiz veya süresi dolmuş kod"));

        if (resetCode.getExpiryDate().isBefore(LocalDateTime.now())) {
            passwordResetCodeService.delete(resetCode);
            throw new RuntimeException("Kodun süresi dolmuş");
        }

        User user = resetCode.getUser();
        String hashedPassword = passwordEncoderConfig.encode(request.getNewPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        passwordResetCodeService.delete(resetCode);
    }

}
