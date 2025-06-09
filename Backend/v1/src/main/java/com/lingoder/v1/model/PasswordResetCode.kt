package com.lingoder.v1.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "password_reset_code")
data class PasswordResetCode(
    @Id
    val id: UUID?,

    @Column(nullable = false, unique = true)
    var code: String,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val expiryDate: LocalDateTime
){
    constructor(code: String, user: User, expiryDate: LocalDateTime) :this(
        id = UUID.randomUUID(),
        code = code,
        user = user,
        expiryDate = expiryDate
    )
}
