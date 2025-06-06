package com.lingoder.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "words")
data class Word(
    @Id
    var id: UUID,

    val engWordName: String,
    val turWordName: String,
    val sentence: String,
    val picture: String,
    val createdAt: LocalDateTime,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    val topic: Topic
){
    constructor():this(
        id = UUID.randomUUID(),
        engWordName = "",
        turWordName = "",
        sentence = "",
        picture = "",
        createdAt = LocalDateTime.now(),
        user = User(),
        topic = Topic()
    )
}
