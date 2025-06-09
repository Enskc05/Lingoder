package com.lingoder.v1.controller;

import com.lingoder.v1.dto.StoryCreateRequestDto;
import com.lingoder.v1.dto.StoryListResponseDto;
import com.lingoder.v1.dto.StoryResponseDto;
import com.lingoder.v1.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoryResponseDto> createStory(@RequestBody StoryCreateRequestDto requestDto) {
        return ResponseEntity.ok(storyService.createStory(requestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<StoryListResponseDto> listStories() {
        return ResponseEntity.ok(storyService.listUserStories());
    }
}

