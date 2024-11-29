package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.infrastructure.out.kafka.KafkaProducer
import org.springframework.stereotype.Service
import java.time.LocalDate

interface SuspiciousTransactionFilter {
    fun execute(base: String, countryCode: String, birthDate: LocalDate)

    @Service
    class SuspiciousTransactionFilterService(private val kafkaProducer: KafkaProducer) : SuspiciousTransactionFilter {
        override fun execute(base: String, countryCode: String, birthDate: LocalDate) {
            kafkaProducer.sendMessage("transactions", SimilarityRequest(base, countryCode, birthDate))
        }
    }
}