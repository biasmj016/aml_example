package com.aml.wlf.algorithms.application.port.`in`

import com.aml.wlf.algorithms.application.port.out.SimilarityAlgorithmRepository
import com.aml.wlf.algorithms.domain.Algorithm
import org.springframework.stereotype.Service

interface FetchAlgorithms {
    fun fetch(): List<Algorithm>

    @Service
    class FetchAlgorithmsUsecase(
        private val repository: SimilarityAlgorithmRepository
    ) : FetchAlgorithms {

        override fun fetch(): List<Algorithm> {
            return repository.findAllAlgorithms().sortedByDescending { it.weight }
        }
    }
}