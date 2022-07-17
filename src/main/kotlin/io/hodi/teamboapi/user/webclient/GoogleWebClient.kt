package io.hodi.teamboapi.user.webclient

import com.fasterxml.jackson.annotation.JsonProperty
import io.hodi.teamboapi.config.properties.GoogleProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
class GoogleWebClient(
    val googleWebClient: WebClient,
    val googleProperties: GoogleProperties
) {

    companion object {
        const val GRANT_TYPE: String = "authorization_code"
    }

    fun getIdToken(code: String): String? {
        val tokenParam = TokenRequestBody(
            code = code,
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = "http://localhost:8080",
            grantType = GRANT_TYPE
        )
        val tokenResponseBody = googleWebClient.post()
            .uri(googleProperties.url)
            .bodyValue(tokenParam)
            .retrieve()
            .onStatus(HttpStatus.BAD_REQUEST::equals) {
                Mono.just(IllegalArgumentException())
            }
            .bodyToMono<TokenResponseBody>()
            .block()

        return tokenResponseBody?.idToken
    }

    data class TokenRequestBody(
        val code: String,
        @JsonProperty("client_id")
        val clientId: String,
        @JsonProperty("client_secret")
        val clientSecret: String,
        @JsonProperty("redirect_uri")
        val redirectUri: String,
        @JsonProperty("grant_type")
        val grantType: String
    )

    data class TokenResponseBody(
        @JsonProperty("access_token")
        val accessToken: String?,
        @JsonProperty("id_token")
        val idToken: String?
    )

    fun getProfileInfo(code: String): String? {
        val tokenParam = TokenRequestBody(
            code = code,
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = "http://localhost:8080",
            grantType = GRANT_TYPE
        )
        val tokenResponseBody = googleWebClient.post()
            .uri(googleProperties.url)
            .bodyValue(tokenParam)
            .retrieve()
            .bodyToMono<TokenResponseBody>()
            .block()

        return tokenResponseBody?.idToken
    }
}
