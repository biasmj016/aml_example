package com.aml.wlf.algorithms.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JaccardSimilarityAlgorithmTest {

    @Test
    fun calculate_success() {
        val base = "test name"
        val comparison = "test name"
        val result = JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(100.0, result)
    }

    @Test
    fun calculate_almost_success() {
        val base = "test name"
        val comparison = "test namee"
        val result = JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison)
     System.out.println(result)
        assertTrue(result > 80.0)
    }

    @Test
    fun calculate_fail() {
        val base = "test name"
        val comparison = "xyzbc"
        val result = JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }

    @Test
    fun calculate_similar() {
        val base = "test name"
        val comparison = "test ne"
        val result = JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(result < 70.0 && result > 50.0, true)
    }

    @Test
    fun calculate_empty() {
        val base = ""
        val comparison = ""
        val result = JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }
}