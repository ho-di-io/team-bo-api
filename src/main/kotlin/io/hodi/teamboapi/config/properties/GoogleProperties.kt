package io.hodi.teamboapi.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "google")
data class GoogleProperties(
    var url: String = "",
    var clientId: String = "",
    var clientSecret: String = "",
    var scope: String = ""
)
