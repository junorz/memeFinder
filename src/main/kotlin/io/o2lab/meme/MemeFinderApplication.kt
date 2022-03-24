package io.o2lab.meme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class MemeFinderApplication

fun main(args: Array<String>) {
    runApplication<MemeFinderApplication>(*args)
}
