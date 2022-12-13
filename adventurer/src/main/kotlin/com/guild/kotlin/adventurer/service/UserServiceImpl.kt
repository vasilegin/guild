package com.guild.kotlin.adventurer.service

import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository):UserService{
    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

}