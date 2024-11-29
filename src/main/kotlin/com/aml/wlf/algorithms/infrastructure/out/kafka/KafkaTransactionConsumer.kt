package com.aml.wlf.algorithms.infrastructure.out.kafka

import com.aml.wlf.algorithms.application.port.`in`.service.ScoreSimilarity
import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaTransactionConsumer(
    private val scoreSimilarity: ScoreSimilarity,
    private val kafkaProducer: KafkaProducer
) {

    @KafkaListener(topics = ["transactions"], groupId = "wlf")
    fun consume(request: SimilarityRequest) {
        val response = scoreSimilarity.execute(request)
        println("Response: $response")

        if (response.isSamePerson) {
            println("Suspicious transaction detected!")
            kafkaProducer.sendMessage("suspicious-transactions", response.toString())
        }
    }
}