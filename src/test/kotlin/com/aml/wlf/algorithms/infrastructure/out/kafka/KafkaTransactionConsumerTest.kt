package com.aml.wlf.algorithms.infrastructure.out.kafka

import com.aml.wlf.algorithms.application.port.`in`.service.ScoreSimilarity
import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.application.port.`in`.service.response.SimilarityResponse
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

class KafkaTransactionConsumerTest {

    private val scoreSimilarity = Mockito.mock(ScoreSimilarity::class.java)
    private val kafkaProducer = Mockito.mock(KafkaProducer::class.java)
    private val kafkaTransactionConsumer = KafkaTransactionConsumer(scoreSimilarity, kafkaProducer)

    @Test
    fun testConsume() = runBlocking {
        val request = SimilarityRequest("John Doe", "BGD", LocalDate.of(1990, 1, 1))
        val response = SimilarityResponse("John Doe", "BGD", LocalDate.of(1990, 1, 1), "John Doe", 100.0, true)

        Mockito.`when`(scoreSimilarity.execute(request)).thenReturn(response)

        kafkaTransactionConsumer.consume(request)

        Mockito.verify(kafkaProducer).sendMessage("suspicious-transactions", response.toString())
    }
}