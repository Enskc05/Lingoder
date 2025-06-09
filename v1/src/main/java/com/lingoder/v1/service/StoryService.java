package com.lingoder.v1.service;

import com.lingoder.v1.dto.StoryCreateRequestDto;
import com.lingoder.v1.dto.StoryListResponseDto;
import com.lingoder.v1.dto.StoryResponseDto;
import com.lingoder.v1.helper.OpenAiClient;
import com.lingoder.v1.model.Story;
import com.lingoder.v1.model.User;
import com.lingoder.v1.repository.StoryRepository;
import com.lingoder.v1.security.UserSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserSession userSession;
    private final OpenAiClient openAiClient;

    public StoryService(StoryRepository storyRepository, UserSession userSession, OpenAiClient openAiClient) {
        this.storyRepository = storyRepository;
        this.userSession = userSession;
        this.openAiClient = openAiClient;
    }

    @Transactional
    public StoryResponseDto createStory(StoryCreateRequestDto requestDto) {
        User currentUser = userSession.getCurrentUser();

        // AI'den hikaye ve görsel alınır
        String storyContent = openAiClient.generateStoryContent(requestDto.getWords(), requestDto.getTitle());
        String imageUrl = openAiClient.generateImage(requestDto.getWords(), requestDto.getTitle());

        Story story = new Story();
        story.setUser(currentUser);
        story.setTitle(requestDto.getTitle());
        story.setContent(storyContent);
        story.setImageUrl(imageUrl);
        story.setCreatedAt(LocalDateTime.now());

        storyRepository.save(story);

        return new StoryResponseDto(
                story.getId(),
                story.getTitle(),
                story.getContent(),
                story.getImageUrl(),
                story.getCreatedAt()
        );
    }

    public StoryListResponseDto listUserStories() {
        User currentUser = userSession.getCurrentUser();

        List<StoryResponseDto> storyDtos = storyRepository.findByUser(currentUser).stream()
                .map(story -> new StoryResponseDto(
                        story.getId(),
                        story.getTitle(),
                        story.getContent(),
                        story.getImageUrl(),
                        story.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return new StoryListResponseDto(storyDtos);
    }
}
