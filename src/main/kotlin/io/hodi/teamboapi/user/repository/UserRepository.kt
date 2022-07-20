package io.hodi.teamboapi.user.repository

import io.hodi.teamboapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun save(user: User): User

    fun findBySocialId(socialId: String): Optional<User>

    fun findByEmail(email: String): Optional<User>
}
