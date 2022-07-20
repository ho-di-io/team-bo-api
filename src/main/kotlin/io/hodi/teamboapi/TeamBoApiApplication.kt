package io.hodi.teamboapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TeamBoApiApplication

fun main(args: Array<String>) {
    runApplication<TeamBoApiApplication>(*args)
}
