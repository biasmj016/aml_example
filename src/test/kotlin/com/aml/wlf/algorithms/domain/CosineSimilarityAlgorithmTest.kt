package com.aml.wlf.algorithms.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CosineSimilarityAlgorithmTest {

    @Test
    fun calculate_success() {
        val base = "test name"
        val comparison = "test name"
        val result = CosineSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(100.0, result)
    }

    @Test
    fun calculate_fail() {
        val base = "test name"
        val comparison = "other thing"
        val result = CosineSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }

    @Test
    fun calculate_similar() {
        val base = "test name"
        val comparison = "other name"
        val result = CosineSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(50.0, result)
    }

    @Test
    fun calculate_empty() {
        val base = ""
        val comparison = ""
        val result = CosineSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }
}