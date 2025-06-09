package com.lingoder.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "global_statistics")
data class GlobalStatistic(
    @Id
    val id: UUID,

    var totalQuizzes: Int,
    var totalCorrectAnswers: Int,
    var totalWrongAnswers: Int,

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var user: User? = null
) {
    constructor():this(
        id = UUID.randomUUID(),
        totalQuizzes = 0,
        totalCorrectAnswers = 0,
        totalWrongAnswers = 0,
        user = User()
    )

    fun getAverageScore(): Double {
        val total = totalCorrectAnswers + totalWrongAnswers
        return if (total == 0) 0.0 else (totalCorrectAnswers.toDouble() / total) * 100
    }
}

