package com.aml.wlf.algorithms.domain

object HammingDistanceAlgorithm : SimilarityAlgorithm {
    override fun calculateSimilarity(base: String, comparison: String): Double {
        if (base.isBlank() && comparison.isBlank()) return 0.0
        if (base.length != comparison.length) return 0.0

        val differingCharacters = (0 until base.length).count { base[it] != comparison[it] }
        val similarity = base.length - differingCharacters
        return toPercentage(similarity.toDouble() / base.length)
    }

    private fun toPercentage(value: Double): Double {
        return value * 100.0
    }
}