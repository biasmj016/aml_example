package com.aml.wlf.algorithms.infrastructure.out.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class KafkaNoticeConsumer(
    private val mailSender: JavaMailSender
) {

    @Value("\${notifier.recipient-email}")
    private lateinit var recipientEmail: String

    @Value("\${notifier.title}")
    private lateinit var emailTitle: String

    @Value("\${notifier.content}")
    private lateinit var emailContent: String

    @KafkaListener(topics = ["suspicious-transactions"], groupId = "wlf")
    fun sendNotification(transaction: String) {
        sendEmail(transaction)
    }

    private fun sendEmail(transaction: String) {
        val message = SimpleMailMessage()
        message.setTo(recipientEmail)
        message.subject = emailTitle
        message.text = "$emailContent: $transaction"
        mailSender.send(message)
    }
}