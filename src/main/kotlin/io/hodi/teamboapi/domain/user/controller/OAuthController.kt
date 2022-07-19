package io.hodi.teamboapi.domain.user.controller

import io.hodi.teamboapi.domain.user.service.OAuthService
import io.hodi.teamboapi.global.utils.JwtTokenUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/oauth")
class OAuthController(
    val oAuthService: OAuthService,
    val jwtTokenUtils: JwtTokenUtils
) {

    @GetMapping("/google")
    fun authenticate(@RequestParam code: String) {
        println(oAuthService.authenticate(code))
    }

    @GetMapping
    fun test(): String? {
        return jwtTokenUtils.createToken()
    }

    @GetMapping("/{test}")
    fun isTest(@PathVariable test: String): String? {
        return jwtTokenUtils.isValidToken(test).toString()
    }
}
