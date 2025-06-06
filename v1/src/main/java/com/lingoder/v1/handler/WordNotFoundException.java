package com.lingoder.v1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {

  public WordNotFoundException() {
    super("Kelime bulunamadı");
  }

  public WordNotFoundException(String message) {
    super(message);
  }

  public WordNotFoundException(UUID wordId) {
    super("ID'si " + wordId + " olan kelime bulunamadı");
  }

  public WordNotFoundException(String word, boolean isEnglish) {
    super(isEnglish
            ? "İngilizce kelime '" + word + "' bulunamadı"
            : "Türkçe kelime '" + word + "' bulunamadı");
  }

  public WordNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
