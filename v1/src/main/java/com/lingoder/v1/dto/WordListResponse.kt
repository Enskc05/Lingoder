package com.lingoder.v1.dto

import java.util.UUID

data class WordListResponse(
    val id: UUID,
    val topic: String,
    val engWordName: String,
    val turkWordName: String,
    val sentences: String,
    val accuracy: Double,
    val image: String
)
