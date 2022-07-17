package io.hodi.teamboapi.user.service

import io.hodi.teamboapi.user.webclient.GoogleWebClient
import org.springframework.stereotype.Service

@Service
class OAuthService(
    val googleWebClient: GoogleWebClient
) {

    fun authenticate(code: String) {
        val idToken = googleWebClient.getIdToken(code)

        idToken.let {
        }
    }
}
