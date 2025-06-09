package com.lingoder.v1.dto

import java.time.LocalDateTime
import java.util.UUID

data class StoryResponseDto(
    val id: UUID,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdAt: LocalDateTime
)