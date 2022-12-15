package com.guild.kotlin.adventurer.service

import java.util.*

interface IService<T> {
    fun findAll(): Collection<T>?
    fun findById(id: Long?): Optional<T>?
    fun saveOrUpdate(t: T): T
    fun deleteById(id: Long?): String?
}
