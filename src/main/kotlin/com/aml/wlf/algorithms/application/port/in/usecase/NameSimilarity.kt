package com.aml.wlf.algorithms.application.port.`in`.usecase

import com.aml.wlf.algorithms.domain.*
import org.springframework.stereotype.Service

interface NameSimilarity {
    fun calculate(base: String, comparison: String, algorithms: List<Algorithm>): Double

    @Service
    class NameSimilarityUsecase : NameSimilarity {
        private val similarityStrategies = mapOf(
            "CosineSimilarity" to CosineSimilarityAlgorithm::calculateSimilarity,
            "DiceCoefficient" to DiceCoefficientAlgorithm::calculateSimilarity,
            "HammingDistance" to HammingDistanceAlgorithm::calculateSimilarity,
            "JaccardSimilarity" to JaccardSimilarityAlgorithm::calculateSimilarity,
            "JaroWinklerDistance" to JaroWinklerDistanceAlgorithm::calculateSimilarity
        )

        override fun calculate(base: String, comparison: String, algorithms: List<Algorithm>): Double {
            val totalWeight = algorithms.sumOf { it.weight }
            if (totalWeight == 0.0) return 0.0

            return algorithms.sumOf { it.weightedSimilarity(base, comparison) } / totalWeight
        }

        private fun Algorithm.weightedSimilarity(base: String, comparison: String): Double {
            val similarity = similarityStrategies[type]?.invoke(base, comparison) ?: 0.0
            return similarity * weight
        }
    }
}
