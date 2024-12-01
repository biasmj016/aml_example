package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.service.SuspiciousTransactionFilter.SuspiciousTransactionFilterService
import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.infrastructure.out.kafka.KafkaProducer
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

class SuspiciousTransactionFilterServiceTest {

    private val kafkaProducer = Mockito.mock(KafkaProducer::class.java)
    private val suspiciousTransactionFilterService = SuspiciousTransactionFilterService(kafkaProducer)

    @Test
    fun testExecute() = runBlocking {
        val base = "John Doe"
        val countryCode = "BGD"
        val birthDate = LocalDate.of(1990, 1, 1)

        suspiciousTransactionFilterService.execute(base, countryCode, birthDate)

        Mockito.verify(kafkaProducer).sendMessage("transactions", SimilarityRequest(base, countryCode, birthDate))
    }
}