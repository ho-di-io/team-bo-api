package io.hodi.teamboapi.utils

import org.springframework.security.core.userdetails.UserDetailsService

class JwtUtils(private val userDetailsService: UserDetailsService) {

    companion object {
        const val JWT_SECRET: String = "thisissecretkey"
    }
}
