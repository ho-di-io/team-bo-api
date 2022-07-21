package io.hodi.teamboapi.user.model

import io.hodi.teamboapi.model.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    val uuid: UUID,

    @Column(name = "socialId", nullable = false)
    val socialId: String,

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role, // ROLE_GUEST, ROLE_USER, ROLE_MANAGER, ROLE_ADMIN

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "part")
    val part: String,

    @Column(name = "intro")
    val intro: String,

    @Column(name = "avatar")
    val avatar: String,

    @Column(name = "active")
    @Enumerated(EnumType.STRING)
    val active: Active
) : BaseEntity()
