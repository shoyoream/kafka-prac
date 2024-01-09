package shoyoream.server.kafkaprac.message.application

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    fun sendMessage(topic: String, msg: String) {
        kafkaTemplate.send(topic, msg)
    }
}
