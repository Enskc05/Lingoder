package com.lingoder.v1.repository;

import com.lingoder.v1.model.Topic;
import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {
    List<Word> findByUser(User user);
    Optional<Word> findById(UUID id);
    List<Word> findByUserAndCreatedAtAfter(User user, LocalDateTime after);

    @Query(value = "SELECT * FROM words WHERE user_id = UUID_TO_BIN(:userId)", nativeQuery = true)
    List<Word> findByUserId(@Param("userId") String userId);

}
