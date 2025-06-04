package com.lingoder.backend.dto

data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String
)
