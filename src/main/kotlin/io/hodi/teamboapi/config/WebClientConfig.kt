package io.hodi.teamboapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration

@Configuration
class WebClientConfig {

    fun httpClient(): HttpClient {
        return HttpClient.create(
            ConnectionProvider.builder("connection-pool")
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
            .clientConnector(
                ReactorClientHttpConnector(
                    httpClient()
                )
            )
            .build()
    }
}
