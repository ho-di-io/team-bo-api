package io.hodi.teamboapi.config.oauth

import io.hodi.teamboapi.user.model.User
import io.hodi.teamboapi.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(socialId: String): UserDetails {
        val user: User = userRepository.findBySocialId(socialId)
            ?: throw UsernameNotFoundException("존재하지 않는 socialId 입니다.")
        return UserDetailsImpl(user)
    }
}
