package shoyoream.server.kafkaprac.message.application

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    fun sendMessage(topic: String, msg: String) {
        kafkaTemplate.send(topic, msg)
    }

    @KafkaListener(topics = ["thisistopic"], groupId = "group_1")
    fun consume(message: String) {
        println("CONSUME MESSAGE : $message")
    }
}
