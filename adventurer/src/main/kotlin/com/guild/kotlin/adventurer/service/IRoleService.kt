package com.guild.kotlin.adventurer.service

interface IRoleService<T> : IService<T> {
    fun findByName(name: String?): T
}