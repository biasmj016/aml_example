package com.aml.wlf.algorithms.application.port.`in`.usecase

import com.aml.wlf.algorithms.domain.*
import org.springframework.stereotype.Service
interface NameSimilarity {
    fun calculate(base: String, comparison: String): Double

    @Service
    class NameSimilarityUsecase(
        private val similarityStrategies: Map<SimilarityType, (String, String) -> Double> = mapOf(
            SimilarityType.COSINE_SIMILARITY to CosineSimilarityAlgorithm::calculateSimilarity,
            SimilarityType.DICE_COEFFICIENT to DiceCoefficientAlgorithm::calculateSimilarity,
            SimilarityType.HAMMING_DISTANCE to HammingDistanceAlgorithm::calculateSimilarity,
            SimilarityType.JACCARD_SIMILARITY to JaccardSimilarityAlgorithm::calculateSimilarity,
            SimilarityType.JARO_WINKLER_DISTANCE to JaroWinklerDistanceAlgorithm::calculateSimilarity
        )
    ) : NameSimilarity {

        private val algorithms = SimilarityType.entries

        override fun calculate(base: String, comparison: String): Double {
            val totalWeight = algorithms.sumOf { it.weight }
            if (totalWeight == 0.0) return 0.0

            return algorithms.sumOf { it.weightedSimilarity(base, comparison) } / totalWeight
        }

        private fun SimilarityType.weightedSimilarity(base: String, comparison: String): Double {
            val similarity = similarityStrategies[this]?.invoke(base, comparison) ?: 0.0
            return similarity * weight
        }
    }
}