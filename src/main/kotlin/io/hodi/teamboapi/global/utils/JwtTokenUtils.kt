package io.hodi.teamboapi.global.utils

import io.hodi.teamboapi.global.config.properties.JwtProperties
import io.jsonwebtoken.*
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtTokenUtils(
    val jwtProperties: JwtProperties
) {

    fun createToken(): String? {
        val expiredMs = Instant.now().toEpochMilli() + jwtProperties.expiration
        val header = mapOf("alg" to "HS256", "typ" to "jwt")

        return Jwts.builder()
            .setHeader(header)
            .setSubject("test")
            .setIssuer(jwtProperties.iss)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.ofEpochMilli(expiredMs)))
            .signWith(
                SignatureAlgorithm.HS256,
                jwtProperties.key.encodeToByteArray())
            .compact()
    }

    fun isValidToken(token: String) {
        try {
            val claims = getClaims(token)
        } catch (e: ExpiredJwtException) {
            throw IllegalArgumentException("dd")
        } catch (e: JwtException) {
            throw IllegalArgumentException("dd")
        }
    }

    private fun getClaims(token: String): Claims? {
        return Jwts.parser()
            .setSigningKey(jwtProperties.key.encodeToByteArray())
            .parseClaimsJws(token)
            .body;
    }
}
