package com.lingoder.v1.dto

data class GlobalStatDto(
    val totalQuizzes: Int,
    val totalCorrectAnswers: Int,
    val totalWrongAnswers: Int,
    val averageScore: Double
)
