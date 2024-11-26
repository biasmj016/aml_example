package com.aml.wlf.algorithms.application.port.out

import com.aml.wlf.algorithms.domain.Algorithm
import com.aml.wlf.algorithms.infrastructure.out.repository.SimilarityAlgorithmJpaRepository
import org.springframework.stereotype.Repository

interface SimilarityAlgorithmRepository {
    fun findAllAlgorithms(): List<Algorithm>

    @Repository
    class SimilarityAlgorithmRepositoryImpl(
        private val jpaRepository: SimilarityAlgorithmJpaRepository
    ) : SimilarityAlgorithmRepository {

        override fun findAllAlgorithms(): List<Algorithm> {
            return jpaRepository.findAll()
                .map { it.toDomain() }
        }

    }
}