package com.guild.kotlin.adventurer.service

import com.guild.kotlin.adventurer.entities.User

interface UserService {
    fun findAll(): List<User>
}