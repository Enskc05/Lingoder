package com.lingoder.v1.service;

import com.lingoder.v1.model.PasswordResetCode;
import com.lingoder.v1.model.User;
import com.lingoder.v1.repository.PasswordResetCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetCodeService {
    private final PasswordResetCodeRepository passwordResetCodeRepository;

    public PasswordResetCodeService(PasswordResetCodeRepository passwordResetCodeRepository) {
        this.passwordResetCodeRepository = passwordResetCodeRepository;
    }

    public void deleteByUser(User user){
        passwordResetCodeRepository.deleteByUser(user);
    }
    public PasswordResetCode save(PasswordResetCode resetCode){
        return passwordResetCodeRepository.save(resetCode);
    }
    public Optional<PasswordResetCode> findByCode(String code) {
        return passwordResetCodeRepository.findByCode(code);
    }
    public void delete(PasswordResetCode resetCode){
         passwordResetCodeRepository.delete(resetCode);
    }
}
