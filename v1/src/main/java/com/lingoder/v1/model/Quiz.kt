package com.lingoder.v1.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "quiz")
data class Quiz(
    @Id
    val id: UUID,

    var correctCount: Int,
    var lastCorrectDate: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    var word: Word
){
    constructor():this(
        id = UUID.randomUUID(),
        correctCount = 0,
        lastCorrectDate = LocalDateTime.now(),
        user = User(),
        word = Word()
    )
}
