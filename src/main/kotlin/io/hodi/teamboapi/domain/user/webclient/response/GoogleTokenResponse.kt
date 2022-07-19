package io.hodi.teamboapi.domain.user.webclient.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GoogleTokenResponse(
    val accessToken: String,
    val idToken: String
)
