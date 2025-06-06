package com.lingoder.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "word_statistics")
data class WordStatistic(
    @Id
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "word_id")
    val word: Word,

    var correctCount: Int,
    var wrongCount: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    val user: User
) {
    constructor(): this(
        id = UUID.randomUUID(),
        word = Word(),
        correctCount = 0,
        wrongCount = 0,
        user = User()
    )
}
