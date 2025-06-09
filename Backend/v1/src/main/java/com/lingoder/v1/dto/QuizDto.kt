package com.lingoder.v1.dto

import java.util.UUID

data class QuizDto(
    val id: UUID,
    val engWordName: String,
    val turWordName: String,
    val imagePath: String,
    val sentence: String
)
