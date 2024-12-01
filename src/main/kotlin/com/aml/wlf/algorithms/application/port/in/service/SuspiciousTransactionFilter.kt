package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.infrastructure.out.kafka.KafkaProducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDate

interface SuspiciousTransactionFilter {
    suspend fun execute(base: String, countryCode: String, birthDate: LocalDate)

    @Service
    class SuspiciousTransactionFilterService(private val kafkaProducer: KafkaProducer) : SuspiciousTransactionFilter {
        override suspend fun execute(base: String, countryCode: String, birthDate: LocalDate) {
            withContext(Dispatchers.IO) {
                kafkaProducer.sendMessage("transactions", SimilarityRequest(base, countryCode, birthDate))
            }
        }
    }
}