package com.guild.kotlin.adventurer.service

import com.guild.kotlin.adventurer.entities.User

interface serService {
    fun findAll(): List<User>
}