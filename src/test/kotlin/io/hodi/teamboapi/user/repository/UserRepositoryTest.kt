package io.hodi.teamboapi.user.repository

import io.hodi.teamboapi.user.model.Active
import io.hodi.teamboapi.user.model.Role
import io.hodi.teamboapi.user.model.User
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalStateException
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class UserRepositoryTest(
    @Autowired val userRepository: UserRepository
) {

    @Test
    fun save() {
        val user = User(
            uuid = UUID.randomUUID(),
            socialId = "101957368256422235993",
            role = Role.ROLE_USER,
            name = "호더",
            email = "hoder@naver.com",
            part = "backend",
            intro = "안녕하세요 잘 부탁드립니다.",
            avatar = "",
            active = Active.A
        )

        val savedUser = userRepository.save(user)

        println("uuid: ${savedUser.uuid}")
        println("savedUser: ${savedUser.name}")
        println("createdAt: ${savedUser.createdAt}")
        println("updatedAt: ${savedUser.updatedAt}")
        assertEquals(savedUser.uuid, user.uuid)
        assertEquals(savedUser.name, user.name)
    }

    @Test
    fun findAll() {
        val user1 = User(
            uuid = UUID.randomUUID(),
            socialId = "101957368256422235993",
            role = Role.ROLE_USER,
            name = "호더1",
            email = "hoder1@naver.com",
            part = "backend",
            intro = "안녕하세요 잘 부탁드립니다.",
            avatar = "",
            active = Active.A
        )
        userRepository.save(user1)

        val user2 = User(
            uuid = UUID.randomUUID(),
            socialId = "201957368256422235993",
            role = Role.ROLE_USER,
            name = "호더2",
            email = "hoder2@naver.com",
            part = "backend",
            intro = "안녕하세요 잘 부탁드립니다.",
            avatar = "",
            active = Active.A
        )
        userRepository.save(user2)

        val result: List<User> = userRepository.findAll()

        println("findAll 사이즈: ${result.size}")
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun findBySocialId() {
        val user = User(
            uuid = UUID.randomUUID(),
            socialId = "101957368256422235993",
            role = Role.ROLE_USER,
            name = "호더",
            email = "hoder@naver.com",
            part = "backend",
            intro = "안녕하세요 잘 부탁드립니다.",
            avatar = "",
            active = Active.A
        )
        userRepository.save(user)

        val findUser = userRepository.findBySocialId(user.socialId)
            .orElseThrow { IllegalStateException("User not found") }

        println("findUser: ${findUser.name}")
        println("user: ${user.name}")
        assertEquals(findUser.uuid, user.uuid)
        assertEquals(findUser.name, user.name)
    }

    @Test
    fun findByEmail() {
        val user = User(
            uuid = UUID.randomUUID(),
            socialId = "101957368256422235993",
            role = Role.ROLE_USER,
            name = "호더",
            email = "hoder@naver.com",
            part = "backend",
            intro = "안녕하세요 잘 부탁드립니다.",
            avatar = "",
            active = Active.A
        )
        userRepository.save(user)

        val findUser = userRepository.findByEmail(user.email)
            .orElseThrow { IllegalStateException("User not found") }

        assertEquals(findUser.uuid, user.uuid)
        assertEquals(findUser.name, user.name)
    }
}
