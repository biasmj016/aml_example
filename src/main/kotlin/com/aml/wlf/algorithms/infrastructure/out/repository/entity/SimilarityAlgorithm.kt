package com.aml.wlf.algorithms.infrastructure.out.repository.entity

import com.aml.wlf.algorithms.domain.Algorithm
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "similarity_algorithms")
class SimilarityAlgorithm(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val type: String = "",
    private val weight: Double = 0.0,
    private val createAt: LocalDateTime = LocalDateTime.now()
) {

    constructor() : this(
        id = null,
        type = "",
        weight = 0.0,
        createAt = LocalDateTime.now()
    )

    constructor(algorithm: Algorithm) : this(
        id = null,
        type = algorithm.type,
        weight = algorithm.weight,
        createAt = LocalDateTime.now()
    )

    fun toDomain(): Algorithm {
        return Algorithm(id ?: 0L, type, weight)
    }
}