package com.lingoder.v1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Kullanıcı bulunamadı");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Long userId) {
        super("ID'si " + userId + " olan kullanıcı bulunamadı");
    }

    public UserNotFoundException(String username, boolean isUsername) {
        super(isUsername
                ? "Kullanıcı adı '" + username + "' olan kullanıcı bulunamadı"
                : "Email '" + username + "' olan kullanıcı bulunamadı");
    }
}
