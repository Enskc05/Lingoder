package com.lingoder.v1.repository;

import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import com.lingoder.v1.model.WordStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordStatisticRepository extends JpaRepository<WordStatistic, UUID> {
    List<WordStatistic> findAllByUser(User user);
    Optional<WordStatistic> findByUserAndWord(User user, Word word);

}
