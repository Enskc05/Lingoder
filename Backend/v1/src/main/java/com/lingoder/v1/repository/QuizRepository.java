package com.lingoder.v1.repository;

import com.lingoder.v1.model.Quiz;
import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    Optional<Quiz> findByWordAndUser(Word word, User user);
    List<Quiz> findByUser(User user);
    List<Quiz> findByWord(Word word);
 }
