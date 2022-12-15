package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import java.util.ArrayList


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SecurityUser



@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(login: String): org.springframework.security.core.userdetails.User {
        val user: User = userRepository?.findByLogin(login) ?: throw UsernameNotFoundException("Login $login not found")
        return SecurityUser(user.login, user.password, getGrantedAuthority(user))
    }

    private fun getGrantedAuthority(user: User): Collection<GrantedAuthority> {
        val authorities: MutableCollection<GrantedAuthority> = ArrayList<GrantedAuthority>()
        if (user.role?.name.equals("admin", ignoreCase = true)) {
            authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        return authorities
    }
}