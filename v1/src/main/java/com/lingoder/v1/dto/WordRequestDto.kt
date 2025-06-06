package com.lingoder.v1.dto

import com.lingoder.v1.model.Topic

data class WordRequestDto(
    val username: String,
    val engWordName: String,
    val turWordName: String,
    val topic: String,
    val picture: String,
    val sentence: String

)
