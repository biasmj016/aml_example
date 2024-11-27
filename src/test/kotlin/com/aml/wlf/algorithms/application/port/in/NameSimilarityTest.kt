package com.aml.wlf.algorithms.application.port.`in`

import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.algorithms.domain.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NameSimilarityTest {
    @Autowired
    private lateinit var usecase: NameSimilarity.NameSimilarityUsecase

    @Test
    fun calculate_low() {
        val base = "test name"
        val comparison = "abcd efgh"
        val algorithms = listOf(
            Algorithm(1L, "CosineSimilarity", 0.3),
            Algorithm(2L, "DiceCoefficient", 0.2),
            Algorithm(3L, "HammingDistance", 0.1),
            Algorithm(4L, "JaccardSimilarity", 0.2),
            Algorithm(5L, "JaroWinklerDistance", 0.2)
        )

        val result = usecase.calculate(base, comparison, algorithms)
        System.out.println(result)

        val expectedSimilarity = (
                CosineSimilarityAlgorithm.calculateSimilarity(base, comparison) * 0.3 +
                        DiceCoefficientAlgorithm.calculateSimilarity(base, comparison) * 0.2 +
                        HammingDistanceAlgorithm.calculateSimilarity(base, comparison) * 0.1 +
                        JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison) * 0.2 +
                        JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison) * 0.2
                ) / 1.0

        System.out.println(expectedSimilarity)
        assertEquals(expectedSimilarity, result)
    }

    @Test
    fun calculate_high() {
        val base = "example"
        val comparison = "example"
        val algorithms = listOf(
            Algorithm(1L, "CosineSimilarity", 0.3),
            Algorithm(2L, "DiceCoefficient", 0.2),
            Algorithm(3L, "HammingDistance", 0.1),
            Algorithm(4L, "JaccardSimilarity", 0.2),
            Algorithm(5L, "JaroWinklerDistance", 0.2)
        )

        val result = usecase.calculate(base, comparison, algorithms)
        System.out.println(result)

        val expectedSimilarity = (
                CosineSimilarityAlgorithm.calculateSimilarity(base, comparison) * 0.3 +
                        DiceCoefficientAlgorithm.calculateSimilarity(base, comparison) * 0.2 +
                        HammingDistanceAlgorithm.calculateSimilarity(base, comparison) * 0.1 +
                        JaccardSimilarityAlgorithm.calculateSimilarity(base, comparison) * 0.2 +
                        JaroWinklerDistanceAlgorithm.calculateSimilarity(base, comparison) * 0.2
                ) / 1.0

        System.out.println(expectedSimilarity)
        assertEquals(expectedSimilarity, result)
    }
}