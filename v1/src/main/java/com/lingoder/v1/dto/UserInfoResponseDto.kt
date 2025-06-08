package com.lingoder.v1.dto

import java.util.UUID

data class UserInfoResponseDto(
    val id: UUID,
    val username: String,
    val email: String,
)
