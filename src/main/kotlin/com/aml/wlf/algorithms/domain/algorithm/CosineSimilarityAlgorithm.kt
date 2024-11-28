package com.aml.wlf.algorithms.domain.algorithm

import com.aml.wlf.algorithms.domain.SimilarityAlgorithm
import kotlin.math.sqrt
import kotlin.math.round

object CosineSimilarityAlgorithm : SimilarityAlgorithm {
    override fun calculateSimilarity(base: String, comparison: String): Double {
        val baseVector = textToVector(base)
        val comparisonVector = textToVector(comparison)

        val dotProduct = calculateDotProduct(baseVector, comparisonVector)
        val magnitudeBase = calculateMagnitude(baseVector)
        val magnitudeComparison = calculateMagnitude(comparisonVector)

        return if (magnitudeBase != 0.0 && magnitudeComparison != 0.0) {
            toPercentage(dotProduct / (magnitudeBase * magnitudeComparison))
        } else 0.0
    }

    private fun textToVector(text: String): Map<String, Int> {
        return Regex("[a-zA-Z0-9]+")
            .findAll(text.lowercase())
            .map { it.value }
            .groupingBy { it }
            .eachCount()
    }

    private fun calculateDotProduct(vector1: Map<String, Int>, vector2: Map<String, Int>): Double {
        return vector1.keys.intersect(vector2.keys).sumOf { vector1[it]!! * vector2[it]!! }.toDouble()
    }

    private fun calculateMagnitude(vector: Map<String, Int>): Double {
        return sqrt(vector.values.sumOf { it * it }.toDouble())
    }

    private fun toPercentage(value: Double): Double {
        return round(value * 10000) / 100
    }
}