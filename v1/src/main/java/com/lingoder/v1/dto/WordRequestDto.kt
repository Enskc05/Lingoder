package com.lingoder.v1.dto


data class WordRequestDto(
    val username: String,
    val engWordName: String,
    val turWordName: String,
    val topic: String,
    val picture: String,
    val sentence: String

)
