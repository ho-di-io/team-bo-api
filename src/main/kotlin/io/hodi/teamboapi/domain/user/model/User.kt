package io.hodi.teamboapi.user.model

import io.hodi.teamboapi.model.BaseEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "socialId", nullable = false)
    val socialId: String,

    @Column(name = "role", nullable = false)
    @ColumnDefault("ROLE_GUEST")
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
