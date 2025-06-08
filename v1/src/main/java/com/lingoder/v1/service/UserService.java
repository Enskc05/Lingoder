package com.lingoder.v1.service;

import com.lingoder.v1.dto.UserInfoResponseDto;
import com.lingoder.v1.model.User;
import com.lingoder.v1.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public User findByUserId(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    public UserInfoResponseDto getCurrentUserInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findByEmail(email);

        return new UserInfoResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));
    }
}
