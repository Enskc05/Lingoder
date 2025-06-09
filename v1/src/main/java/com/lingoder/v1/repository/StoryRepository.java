package com.lingoder.v1.repository;

import com.lingoder.v1.model.Story;
import com.lingoder.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoryRepository extends JpaRepository<Story, UUID> {
    List<Story> findByUser(User user);
}
