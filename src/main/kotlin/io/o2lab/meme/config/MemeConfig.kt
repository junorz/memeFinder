package io.o2lab.meme.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "memes")
data class MemeConfig(
    val urlPrefix: String,
)
