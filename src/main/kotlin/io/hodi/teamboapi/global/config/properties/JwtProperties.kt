package io.hodi.teamboapi.global.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    var iss: String = "",
    var key: String = "",
    var expiration: Long = 0L
)
