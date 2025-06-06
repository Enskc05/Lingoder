package com.lingoder.v1.dto

data class StatisticResponseDto(
    val mostKnownWords: List<WordStatDto>,
    val categoryStats:  List<TopicStatDto>,
    val globalStats: GlobalStatDto
)
