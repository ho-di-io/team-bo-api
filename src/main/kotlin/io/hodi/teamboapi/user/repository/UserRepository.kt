package io.hodi.teamboapi.user.repository

import io.hodi.teamboapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findBySocialId(socialId: String): User?
}
