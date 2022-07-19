package io.hodi.teamboapi.domain.user.webclient.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GoogleProfileInfoResponse(
    @JsonProperty("sub")
    val id: String,
    val email: String,
    val name: String
)
