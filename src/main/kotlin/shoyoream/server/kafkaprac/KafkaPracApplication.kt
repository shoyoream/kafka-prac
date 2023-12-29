package shoyoream.server.kafkaprac

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaPracApplication

fun main(args: Array<String>) {
    runApplication<KafkaPracApplication>(*args)
}
