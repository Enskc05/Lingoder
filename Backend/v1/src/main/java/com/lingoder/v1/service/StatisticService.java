package com.lingoder.v1.service;

import com.lingoder.v1.dto.GlobalStatDto;
import com.lingoder.v1.dto.StatisticResponseDto;
import com.lingoder.v1.dto.TopicStatDto;
import com.lingoder.v1.dto.WordStatDto;
import com.lingoder.v1.model.User;
import com.lingoder.v1.security.UserSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    private final UserSession userSession;

    public StatisticService(UserSession userSession) {
        this.userSession = userSession;
    }

    public StatisticResponseDto getUserStatistics() {
        User user = userSession.getCurrentUser();
        List<WordStatDto> mostKnownWords = user.getWordStatistics().stream()
                .sorted((a, b) -> b.getCorrectCount() - a.getCorrectCount())
                .limit(3)
                .filter(ws -> ws.getWord() != null)
                .map(ws -> new WordStatDto(ws.getWord().getEngWordName(), ws.getCorrectCount()))
                .collect(Collectors.toList());
        List<TopicStatDto> topicStats = user.getTopicStatistics().stream()
                .map(ts -> {
                    double accuracy = ts.getTotal() == 0 ? 0.0 : ((double) ts.getCorrect() / ts.getTotal()) * 100;
                    return new TopicStatDto(ts.getTopic(), ts.getCorrect(), ts.getTotal(), accuracy);
                })
                .collect(Collectors.toList());

        var global = user.getGlobalStatistic();
        int total = global.getTotalCorrectAnswers() + global.getTotalWrongAnswers();
        double averageScore = total == 0 ? 0.0 : ((double) global.getTotalCorrectAnswers() / total) * 100;

        return new StatisticResponseDto(
                mostKnownWords,
                topicStats,
                new GlobalStatDto(
                        global.getTotalQuizzes(),
                        global.getTotalCorrectAnswers(),
                        global.getTotalWrongAnswers(),
                        averageScore
                )
        );

    }
}

