package shoyoream.server.kafkaprac.message.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import shoyoream.server.kafkaprac.message.application.MessageService

@RestController
@RequestMapping("/message")
class MessageController(
    private val messageService: MessageService
) {
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("")
    fun sendMessage(
        @Valid @RequestParam topic: String,
        @Valid @RequestParam msg: String
    ) {
        messageService.sendMessage(topic, msg)
    }
}
