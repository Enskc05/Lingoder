package com.lingoder.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "topic_statistics")
data class TopicStatistic(
    @Id
    val id: UUID,

    val topic: String,

    var correct: Int,
    var total: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    val user: User
) {
    constructor():this(
        id = UUID.randomUUID(),
        topic = "",
        correct = 0,
        total = 0,
        user = User()
    )

    fun getAccuracy(): Double {
        return if (total == 0) 0.0 else (correct.toDouble() / total) * 100
    }
}

