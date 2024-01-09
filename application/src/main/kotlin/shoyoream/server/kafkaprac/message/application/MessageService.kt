package shoyoream.server.kafkaprac.message.application

import org.apache.kafka.clients.consumer.ConsumerRecord
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

    @KafkaListener(topics = ["thisistopic"], groupId = "group_1", containerFactory = "kafkaListenerContainerFactory")
    fun consume(message: ConsumerRecord<String, Any>) {
        println("CONSUME MESSAGE : $message")
    }
}
