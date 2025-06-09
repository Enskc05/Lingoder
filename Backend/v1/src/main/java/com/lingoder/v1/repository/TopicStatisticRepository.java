package com.lingoder.v1.repository;

import com.lingoder.v1.model.TopicStatistic;
import com.lingoder.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TopicStatisticRepository extends JpaRepository<TopicStatistic, UUID> {
    List<TopicStatistic> findAllByUser(User user);
}
