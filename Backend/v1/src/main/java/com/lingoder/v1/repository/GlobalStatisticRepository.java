package com.lingoder.v1.repository;

import com.lingoder.v1.model.GlobalStatistic;
import com.lingoder.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GlobalStatisticRepository extends JpaRepository<GlobalStatistic, UUID> {
    Optional<GlobalStatistic> findByUser(User user);

}
