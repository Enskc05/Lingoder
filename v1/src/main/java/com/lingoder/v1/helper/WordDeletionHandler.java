package com.lingoder.v1.helper;

import com.lingoder.v1.model.User;
import com.lingoder.v1.model.Word;
import com.lingoder.v1.repository.WordRepository;
import com.lingoder.v1.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class WordDeletionHandler {
    private final WordRepository wordRepository;
    private final UserService userService;

    public WordDeletionHandler(WordRepository wordRepository, UserService userService) {
        this.wordRepository = wordRepository;

        this.userService = userService;
    }

    public void prepareForDeletion(Word word) {
        removeFromUser(word);
    }



    private void removeFromUser(Word word) {
        User user = word.getUser();
        if (user != null) {
            user.getWords().remove(word);
            userService.save(user);
        }
    }

}
