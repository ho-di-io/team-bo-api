package io.hodi.teamboapi.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class JwtUtils(private val userDetailsService: UserDetailsService) {

    companion object {
        private const val EXP_TIME: Long = 1000L * 60 * 3
        private var JWT_SECRET: String = "thisistestsecretkey"
    }

    @PostConstruct
    fun init() {
        JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray())
    }

    fun createToken(socialId: String): String {
        val claims: Claims = Jwts.claims()
        claims["socialId"] = socialId

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(Date(System.currentTimeMillis() + EXP_TIME))
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact()
    }

    fun getAuthentication(token: String): Authentication { // jwt 토큰으로 인증 정보 조회
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(getSocialId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getSocialId(token: String): String {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).body.subject
    }

    fun validateToken(token: String): Boolean {
        val claims: Claims = getAllClaims(token)
        val exp: Date = claims.expiration
        return exp.after(Date())
    }

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .body
    }
}
