package com.aml.wlf.algorithms.application.port.`in`

import com.aml.wlf.algorithms.application.port.`in`.usecase.FetchAlgorithms
import com.aml.wlf.algorithms.application.port.out.SimilarityAlgorithmRepository
import com.aml.wlf.algorithms.domain.Algorithm
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FetchAlgorithmsUsecaseTest {

    private val repository = mock(SimilarityAlgorithmRepository::class.java)
    private val fetchAlgorithmsUsecase = FetchAlgorithms.FetchAlgorithmsUsecase(repository)

    @Test
    fun fetch() {
        val algorithms = listOf(
            Algorithm(1L, "Algorithm1", 0.3),
            Algorithm(2L, "Algorithm2", 0.5),
            Algorithm(3L, "Algorithm3", 0.4)
        )
        `when`(repository.findAllAlgorithms()).thenReturn(algorithms)

        val result = fetchAlgorithmsUsecase.fetch()

        assertEquals(
            listOf(
                Algorithm(2L, "Algorithm2", 0.5),
                Algorithm(3L, "Algorithm3", 0.4),
                Algorithm(1L, "Algorithm1", 0.3)
            ), result
        )
    }
}