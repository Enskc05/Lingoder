package com.lingoder.v1.repository;

import com.lingoder.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
