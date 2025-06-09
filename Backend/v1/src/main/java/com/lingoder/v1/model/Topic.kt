package com.lingoder.v1.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID


@Entity
@Table(name = "topics")
data class Topic(
    @Id
    val id: UUID,

    val name: String
){
    constructor():this(
        id = UUID.randomUUID(),
        name = ""
    )
}
