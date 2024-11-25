package com.aml.wlf.algorithms.domain

object HammingDistanceAlgorithm {
    fun calculateSimilarity(base: String, comparison: String): Double {
        val minLength = minOf(base.length, comparison.length)
        val maxLength = maxOf(base.length, comparison.length)
        val result = (0 until minLength).count { base[it] == comparison[it] }

        val distance = maxLength - minLength + result
        return toPercentage(distance.toDouble() / maxLength)
    }

    private fun toPercentage(value: Double): Double {
        return value * 100.0
    }
}