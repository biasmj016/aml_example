package com.aml.wlf.algorithms.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class JaroWinklerDistanceAlgorithmTest {

    @Test
    fun calculate_success() {
        val base = "test name"
        val comparison = "test name"
        val result = JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(100.0, result)
    }

    @Test
    fun calculate_almost_success() {
        val base = "test name"
        val comparison = "test time"
        val result = JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison)
        assertTrue(result > 80.0)
    }

    @Test
    fun calculate_fail() {
        val base = "test name"
        val comparison = "xyzbc"
        val result = JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }

    @Test
    fun calculate_similar() {
        val base = "test name"
        val comparison = "nest"
        val result = JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(result < 70.0 && result > 50.0, true)
    }

    @Test
    fun calculate_empty() {
        val base = ""
        val comparison = ""
        val result = JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison)
        assertEquals(0.0, result)
    }
}