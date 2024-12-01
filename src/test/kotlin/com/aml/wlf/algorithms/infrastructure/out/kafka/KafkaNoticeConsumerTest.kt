package com.aml.wlf.algorithms.infrastructure.out.kafka

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class KafkaNoticeConsumerTest {

    private val mailAddress = "recipientEmail"
    private val mailSender = Mockito.mock(JavaMailSender::class.java)
    private lateinit var kafkaNoticeConsumer: KafkaNoticeConsumer

    @BeforeEach
    fun setUp() {
        kafkaNoticeConsumer = KafkaNoticeConsumer(mailSender)
        setPrivateField(kafkaNoticeConsumer, "recipientEmail", mailAddress)
        setPrivateField(kafkaNoticeConsumer, "emailTitle", "Suspicious Transaction Alert")
        setPrivateField(kafkaNoticeConsumer, "emailContent", "A suspicious transaction has been detected")
    }

    private fun setPrivateField(target: Any, fieldName: String, value: Any) {
        val field = target::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        field.set(target, value)
    }

    @Test
    fun testSendNotification() {
        val transaction = "Suspicious transaction details"
        kafkaNoticeConsumer.sendNotification(transaction)

        val message = SimpleMailMessage()
        message.setTo(mailAddress)
        message.subject = "Suspicious Transaction Alert"
        message.text = "A suspicious transaction has been detected: \n$transaction"

        Mockito.verify(mailSender).send(message)
    }
}
