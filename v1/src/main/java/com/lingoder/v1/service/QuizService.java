package com.lingoder.v1.service;

import com.lingoder.v1.dto.QuizDto;
import com.lingoder.v1.dto.QuizRequestDto;
import com.lingoder.v1.model.Quiz;
import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import com.lingoder.v1.repository.QuizRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final WordService wordService;
    private final UserService userService;

    public QuizService(QuizRepository quizRepository, WordService wordService, UserService userService) {
        this.quizRepository = quizRepository;
        this.wordService = wordService;
        this.userService = userService;
    }

    public Optional<Quiz> findByWordAndUser(Word word, User user){
        return quizRepository.findByWordAndUser(word,user);
    }
    public List<Quiz> findByUser(User user){
        return quizRepository.findByUser(user);
    }
    public List<Quiz> findByWord(Word word){
        return quizRepository.findByWord(word);
    }
    public Quiz save(Quiz quiz){
        return quizRepository.save(quiz);
    }
    public List<QuizDto> getAllQuiz() {
        List<Quiz> quizs = quizRepository.findAll();
        List<QuizDto> dtos = new ArrayList<>();

        for (Quiz quiz : quizs) {
            Word word = quiz.getWord();
            String imagePath = "http://localhost:8080/assets/" + word.getId() + "/" + word.getEngWordName() + ".png";
            dtos.add(new QuizDto(
                    word.getId(),
                    word.getEngWordName(),
                    word.getTurWordName(),
                    imagePath,
                    word.getSentence()
            ));
        }
        return dtos;
    }


    public List<Word> getQuizWords(User user, int count) {
        LocalDateTime todayStart = LocalDateTime.now();
        List<Word> todaysWords = wordService.findByUserAndCreatedAtAfter(user, todayStart);
        List<Quiz> quizs = quizRepository.findByUser(user);
        Map<UUID, Quiz> quizMap = quizs.stream()
                .collect(Collectors.toMap(h -> h.getWord().getId(), h -> h));

        List<Word> eligible = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for (Word word : todaysWords) {
            Quiz q = quizMap.get(word.getId());
            if (q == null || q.getCorrectCount() == 0) {
                eligible.add(word);
                continue;
            }
            if (shouldAskAgain(q, today)) {
                eligible.add(word);
            }
        }
        List<Word> allWords = wordService.findByUser(user);
        for (Word word : allWords) {
            if (todaysWords.contains(word)) continue;
            Quiz q = quizMap.get(word.getId());
            if (q == null || q.getCorrectCount() == 0) {
                eligible.add(word);
                continue;
            }
            if (shouldAskAgain(q, today)) {
                eligible.add(word);
            }
        }
        Collections.shuffle(eligible);
        return eligible.stream().limit(count).collect(Collectors.toList());
    }

    private boolean shouldAskAgain(Quiz q, LocalDateTime today) {
        if (q.getCorrectCount() >= 6) {
            return false;
        }
        if (q.getCorrectCount() == 0 || q.getLastCorrectDate() == null) {
            return true;
        }
        int[] days = {1, 7, 30, 90, 180, 365};
        int idx = Math.max(0, q.getCorrectCount() - 1);
        LocalDateTime nextDate = q.getLastCorrectDate().plusDays(days[idx]);
        return today.isAfter(nextDate);
    }

    public void updateQuizResult(QuizRequestDto dto) {
        User user = userService.findByUserId(dto.getUserId());
        Word word = wordService.findById(dto.getWordId());

        Optional<Quiz> opt = quizRepository.findByWordAndUser(word, user);
        Quiz q = opt.orElseGet(() -> {
            Quiz nq = new Quiz();
            nq.setUser(user);
            nq.setWord(word);
            nq.setCorrectCount(0);
            nq.setLastCorrectDate(null);
            return nq;
        });

        LocalDateTime today = LocalDateTime.now();

        if (dto.getCorrect()) {
            if (q.getLastCorrectDate() == null || !q.getLastCorrectDate().toLocalDate().isEqual(today.toLocalDate())) {
                q.setCorrectCount(q.getCorrectCount() + 1);
                q.setLastCorrectDate(today);
            }
        } else {
            q.setCorrectCount(0);
            q.setLastCorrectDate(null);
        }

        quizRepository.save(q);
    }


    public List<QuizDto> getQuizDtoForUser(User user, int count) {
        List<Word> words = getQuizWords(user, count);
        List<QuizDto> dtos = new ArrayList<>();

        for (Word word : words) {
            String imagePath = "http://localhost:8080/assets/" + word.getId() + "/" + word.getEngWordName() + ".png";
            dtos.add(new QuizDto(
                    word.getId(),
                    word.getEngWordName(),
                    word.getTurWordName(),
                    imagePath,
                    word.getSentence()
            ));
        }

        return dtos;
    }



}
