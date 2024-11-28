package com.aml.wlf.algorithms.domain

interface SimilarityAlgorithm {
    fun calculateSimilarity(base: String, comparison: String): Double
}