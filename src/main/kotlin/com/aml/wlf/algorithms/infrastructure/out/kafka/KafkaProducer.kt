package com.aml.wlf.algorithms.infrastructure.out.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun sendMessage(topic: String, message: Any) {
        kafkaTemplate.send(topic, message)
    }
}