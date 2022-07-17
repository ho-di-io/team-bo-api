package io.hodi.teamboapi.user.service

import io.hodi.teamboapi.user.webclient.GoogleWebClient
import org.springframework.stereotype.Service

@Service
class OAuthService(
    val googleWebClient: GoogleWebClient
) {

    fun authenticate(code: String): String? {
        val googleTokenResponse = googleWebClient.getIdToken(code)

        val idToken = googleTokenResponse?.let {
            it.idToken
        } ?: run {
            throw IllegalArgumentException("데이터 없음")
        }

        val googleProfileInfoResponse = googleWebClient.getProfileInfo(idToken)

        val id = googleProfileInfoResponse?.id

        return id
    }
}
