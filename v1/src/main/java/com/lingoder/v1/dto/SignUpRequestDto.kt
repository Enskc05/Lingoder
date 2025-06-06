package com.lingoder.v1.dto

import com.lingoder.v1.model.UserRole

data class SignUpRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole
)
