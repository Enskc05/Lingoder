package com.lingoder.v1.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "story")
data class Story(
    @Id
    val id: UUID,

    var title: String,

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    var content: String,

    @Column(name = "image_url", length = 1024)
    var imageUrl: String,

    var createdAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User
){
    constructor():this(
        id = UUID.randomUUID(),
        title = "",
        content = "",
        imageUrl = "",
        createdAt = LocalDateTime.now(),
        user = User()
    )
}
