package com.aml.wlf.algorithms.application.port.out

import com.aml.wlf.algorithms.infrastructure.out.repository.SimilarityAlgorithmJpaRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SimilarityAlgorithmRepositoryTest {

    @Autowired
    private lateinit var jpaRepository: SimilarityAlgorithmJpaRepository

    @Test
    fun findAllAlgorithms() {
        val similarityAlgorithmRepository =
            SimilarityAlgorithmRepository.SimilarityAlgorithmRepositoryImpl(jpaRepository)

        val result = similarityAlgorithmRepository.findAllAlgorithms()
        assertNotNull(result)
    }
}