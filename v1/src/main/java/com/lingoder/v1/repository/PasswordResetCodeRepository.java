package com.lingoder.v1.repository;

import com.lingoder.v1.model.PasswordResetCode;
import com.lingoder.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, UUID> {
    Optional<PasswordResetCode> findById(UUID uuid);
    Optional<PasswordResetCode> findByCode(String code);
    void deleteByUser(User user);

}
