package com.lingoder.v1.repository;

import com.lingoder.v1.model.Topic;
import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {
    List<Word> findByUser(User user);

    @Query("SELECT w FROM Word w WHERE w.user = :user AND w.createdAt >= :date")
    List<Word> findByUserAndCreatedAtAfter(@Param("user") User user,
                                           @Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM words WHERE user_id = UUID_TO_BIN(:userId)", nativeQuery = true)
    List<Word> findByUserId(@Param("userId") String userId);

    @Query("SELECT w FROM Word w LEFT JOIN FETCH w.topic WHERE w.user.username = :username")
    List<Word> findByUsernameWithTopic(@Param("username") String username);

}
