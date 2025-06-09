package com.lingoder.v1.controller;

import com.lingoder.v1.dto.QuizDto;
import com.lingoder.v1.dto.QuizRequestDto;
import com.lingoder.v1.model.User;
import com.lingoder.v1.service.QuizService;
import com.lingoder.v1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @PostMapping("/result")
    public ResponseEntity<Void> updateQuizResult(@RequestBody QuizRequestDto dto) {
        quizService.updateQuizResult(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<QuizDto> getQuiz(
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "5") int count
    ) {
        User user = userService.findByUserId(userId);
        return quizService.getQuizDtoForUser(user, count);
    }

    @GetMapping("/all")
    public List<QuizDto> getAllQuiz() {
        return quizService.getAllQuiz();
    }
}
