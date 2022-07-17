package io.hodi.teamboapi.user.controller

import io.hodi.teamboapi.user.service.OAuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/oauth")
class OAuthController(
    val oAuthService: OAuthService
) {

    @GetMapping("/google")
    fun authenticate(@RequestParam code: String) {
        println(oAuthService.authenticate(code))
    }
}
