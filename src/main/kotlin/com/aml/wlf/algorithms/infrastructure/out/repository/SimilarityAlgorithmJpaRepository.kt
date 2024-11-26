package com.aml.wlf.algorithms.infrastructure.out.repository

import com.aml.wlf.algorithms.infrastructure.out.repository.entity.SimilarityAlgorithm
import org.springframework.data.jpa.repository.JpaRepository

interface SimilarityAlgorithmJpaRepository : JpaRepository<SimilarityAlgorithm, Long> {
}