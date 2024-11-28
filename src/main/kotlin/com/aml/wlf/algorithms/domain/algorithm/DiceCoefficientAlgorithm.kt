package com.aml.wlf.algorithms.domain.algorithm

import com.aml.wlf.algorithms.domain.SimilarityAlgorithm
import kotlin.math.round

object DiceCoefficientAlgorithm : SimilarityAlgorithm {
    override fun calculateSimilarity(base: String, comparison: String): Double {
        val baseMap = toMap(base)
        val comparisonMap = toMap(comparison)
        val totalSize = baseMap.size + comparisonMap.size

        if (totalSize == 0) return 0.0

        val intersection = baseMap.keys.intersect(comparisonMap.keys).size

        return toPercentage(2.0 * intersection / totalSize)
    }

    private fun toMap(text: String): Map<String, Int> {
        val regex = "[^a-zA-Z0-9]".toRegex()
        return text.split("\\s+".toRegex())
            .mapNotNull { it.replace(regex, "")
                .takeIf(String::isNotEmpty)?.lowercase() }
            .groupingBy { it }
            .eachCount()
    }

    private fun toPercentage(value: Double): Double {
        return round(value * 10000) / 100
    }
}