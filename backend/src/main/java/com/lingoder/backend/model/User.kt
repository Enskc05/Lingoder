package com.lingoder.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    val id: UUID?,

    @Column(nullable=false , unique=true)
    val username: String,

    @Column(unique = true, nullable = false)
    val email: String,

    private val password: String
){
    constructor():this(
        id = null,
        username = "",
        email = "",
        password = ""
    )
}
