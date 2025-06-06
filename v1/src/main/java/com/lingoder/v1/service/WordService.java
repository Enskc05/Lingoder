package com.lingoder.v1.service;

import com.lingoder.v1.dto.WordListResponse;
import com.lingoder.v1.dto.WordRequestDto;
import com.lingoder.v1.dto.WordResponseDto;
import com.lingoder.v1.helper.WordDeletionHandler;
import com.lingoder.v1.model.Topic;
import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import com.lingoder.v1.model.WordStatistic;
import com.lingoder.v1.repository.TopicRepository;
import com.lingoder.v1.repository.WordRepository;
import com.lingoder.v1.repository.WordStatisticRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final UserService userService;
    private final TopicRepository topicRepository;
    private final WordDeletionHandler wordDeletionHandler;
    private final WordStatisticRepository wordStatisticRepository;

    public WordService(WordRepository wordRepository, UserService userService, TopicRepository topicRepository, WordDeletionHandler wordDeletionHandler, WordStatisticRepository wordStatisticRepository) {
        this.wordRepository = wordRepository;
        this.userService = userService;
        this.topicRepository = topicRepository;
        this.wordDeletionHandler = wordDeletionHandler;
        this.wordStatisticRepository = wordStatisticRepository;
    }
    @Transactional
    public Topic findOrCreateByName(String name) {
        return topicRepository.findByName(name)
                .orElseGet(() -> {
                    Topic newTopic = new Topic(UUID.randomUUID(), name);
                    return topicRepository.save(newTopic);
                });
    }
    @Transactional
    public WordResponseDto add(WordRequestDto request){
        User user = userService.findByUsername(request.getUsername());
        Topic topic = findOrCreateByName((request.getTopic()));
        Word word = new Word(
                UUID.randomUUID(),
                request.getEngWordName(),
                request.getTurWordName(),
                request.getSentence(),
                request.getPicture(),
                LocalDateTime.now(),
                user,
                topic
        );
        Word savedWord = wordRepository.save(word);
        return new WordResponseDto(savedWord.getId());
    }
    @Transactional
    public void delete(UUID id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kelime bulunamadı" + id));

       wordDeletionHandler.prepareForDeletion(word);
        wordRepository.delete(word);
    }


    @Transactional
    public List<WordListResponse> getWordsByUser(String userId) {
        List<Word> words = wordRepository.findByUserId(userId);
        return words.stream()
                .map(this::mapToWordListResponse)
                .collect(Collectors.toList());
    }

    private WordListResponse mapToWordListResponse(Word word) {
        String topicName = word.getTopic() != null ? word.getTopic().getName() : "Belirtilmemiş";
        String picture = word.getPicture() != null ? word.getPicture() : "";

        double accuracy = wordStatisticRepository.findByUserAndWord(word.getUser(), word)
                .map(this::calculateAccuracy)
                .orElse(0.0);

        return new WordListResponse(
                word.getId(),
                topicName,
                word.getEngWordName(),
                word.getTurWordName(),
                word.getSentence(),
                accuracy,
                picture
        );
    }

    private double calculateAccuracy(WordStatistic stat) {
        int total = stat.getCorrectCount() + stat.getWrongCount();
        return total > 0 ? (stat.getCorrectCount() * 100.0 / total) : 0.0;
    }
}
