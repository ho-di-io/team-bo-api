package io.hodi.teamboapi.config.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(private val jwtUtils: JwtUtils) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")
            ?: return filterChain.doFilter(request, response)
        val token = authorizationHeader?.substring("Bearer ".length)
            ?: return filterChain.doFilter(request, response)

        // 토큰 검증
        if (jwtUtils.validateToken(token)) {
            val username = jwtUtils.getSocialId(token)
            val authentication: Authentication = jwtUtils.getAuthentication(username)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}
