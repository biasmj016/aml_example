package com.aml.wlf.algorithms.domain.algorithm

import com.aml.wlf.algorithms.domain.SimilarityAlgorithm
import kotlin.math.round

object JaccardSimilarityAlgorithm : SimilarityAlgorithm {
    override fun calculateSimilarity(base: String, comparison: String): Double {
        if (base.isBlank() && comparison.isBlank()) return 0.0

        val basePairs = letterPairs(base.lowercase())
        val comparisonPairs = letterPairs(comparison.lowercase())
        val intersection = basePairs.intersect(comparisonPairs).size
        val union = basePairs.union(comparisonPairs).size
        return toPercentage(intersection.toDouble() / union)
    }

    private fun letterPairs(str: String): Set<String> {
        return str.windowed(2, 1).toSet()
    }

    private fun toPercentage(value: Double): Double {
        return round(value * 10000) / 100
    }
}