package io.hodi.teamboapi.user.webclient

import io.hodi.teamboapi.config.properties.GoogleProperties
import io.hodi.teamboapi.user.webclient.request.GoogleTokenRequest
import io.hodi.teamboapi.user.webclient.response.GoogleProfileInfoResponse
import io.hodi.teamboapi.user.webclient.response.GoogleTokenResponse
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

    fun getIdToken(code: String): GoogleTokenResponse? {
        val googleTokenRequest = GoogleTokenRequest(
            code = code,
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = googleProperties.redirectUri,
            grantType = GRANT_TYPE
        )
        val googleTokenResponse = googleWebClient.post()
            .uri {
                it.path("/token")
                    .build()
            }
            .bodyValue(googleTokenRequest)
            .retrieve()
            .onStatus(HttpStatus.BAD_REQUEST::equals) {
                Mono.just(IllegalArgumentException())
            }
            .bodyToMono<GoogleTokenResponse>()
            .block()

        return googleTokenResponse
    }

    fun getProfileInfo(idToken: String): GoogleProfileInfoResponse? {
        return googleWebClient.get()
            .uri {
                it.path("/tokeninfo")
                    .queryParam("id_token", idToken)
                    .build()
            }
            .retrieve()
            .bodyToMono<GoogleProfileInfoResponse>()
            .block()
    }
}
