package com.lingoder.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class User(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(unique = true)
    var username: String,

    @Column(unique = true)
    var email: String,

    var password: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    val words: MutableList<Word> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    val wordStatistics: MutableList<WordStatistic> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    val topicStatistics: MutableList<TopicStatistic> = mutableListOf(),

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var globalStatistic: GlobalStatistic? = null
) {
    constructor() : this(
        username = "",
        email = "",
        password = "",
        role = UserRole.USER
    )
}
