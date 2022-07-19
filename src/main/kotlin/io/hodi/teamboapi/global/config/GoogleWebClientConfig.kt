package io.hodi.teamboapi.global.config

import io.hodi.teamboapi.global.config.properties.GoogleProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration

@Configuration
class GoogleWebClientConfig(
    val googleProperties: GoogleProperties
) {

    fun httpClient(): HttpClient {
        return HttpClient.create(
            ConnectionProvider.builder("google-connection-pool")
                .maxLifeTime(Duration.ofSeconds(4L))
                .maxIdleTime(Duration.ofSeconds(4L))
                .pendingAcquireMaxCount(-1)
                .lifo()
                .build()
        )
    }

    @Bean
    fun googleWebclientConfig(): WebClient {
        return WebClient.builder()
            .baseUrl(googleProperties.url)
            .clientConnector(
                ReactorClientHttpConnector(
                    httpClient()
                )
            )
            .build()
    }
}
