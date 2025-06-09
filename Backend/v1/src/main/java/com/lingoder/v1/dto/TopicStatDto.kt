package com.lingoder.v1.dto

data class TopicStatDto(
    val topic: String,
    val correct: Int,
    val total: Int,
    val accuracy: Double
)
