package com.lingoder.v1.controller;

import com.lingoder.v1.dto.WordListResponse;
import com.lingoder.v1.dto.WordRequestDto;
import com.lingoder.v1.dto.WordResponseDto;
import com.lingoder.v1.security.CustomUserDetails;
import com.lingoder.v1.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/word")
@CrossOrigin
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<WordResponseDto> add(@RequestBody WordRequestDto request){
        return ResponseEntity.ok(wordService.add(request));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        wordService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<WordListResponse>> getCurrentUserWords(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = userDetails.getId().toString();
        List<WordListResponse> words = wordService.getWordsByUser(userId);
        return ResponseEntity.ok(words);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<WordListResponse>> getUserWordsById(@PathVariable UUID userId) {
        List<WordListResponse> words = wordService.getWordsByUser(userId.toString());
        return ResponseEntity.ok(words);
    }

}
