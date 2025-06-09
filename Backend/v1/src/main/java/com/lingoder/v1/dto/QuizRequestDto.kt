package com.lingoder.v1.dto

import java.util.UUID

data class QuizRequestDto(
    val userId: UUID,
    val wordId: UUID,
    val correct: Boolean
)
