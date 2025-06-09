package com.lingoder.v1.dto

data class PasswordResetRequestDto(
    val code: String,
    val newPassword: String
)
