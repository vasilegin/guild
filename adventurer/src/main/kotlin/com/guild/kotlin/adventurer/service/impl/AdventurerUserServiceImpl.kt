package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import org.json.JSONObject
import org.json.JSONException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdventurerUserServiceImpl(private val userRepository: UserRepository) {
    fun findAllAdv(pageable: Pageable?): Page<User>? {
        return userRepository.findAllByAdventurerIsNotNull(pageable!!)
    }

    fun findAllByLoginContainingAndAdventurerIsNotNull(pageable: Pageable?, login: String): Page<User>?{
        return userRepository.findAllByLoginContainingAndAdventurerIsNotNull(pageable, login)
    }

    fun findAll(pageable: Pageable?): Page<User> {
        return userRepository.findAll(pageable!!)
    }

    fun findAll(): List<User>? {
        return userRepository.findAllByAdventurerIsNotNull()
    }

    fun findById(id: Long?): Optional<User>? {
        return userRepository.findByIdAndAdventurerIsNotNull(id!!)
    }

    fun deleteById(id: Long?): String? {
        val jsonObject = JSONObject()
        try {
            userRepository.deleteById(id!!)
            jsonObject.put("message", "User deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }

    fun saveOrUpdate(user: User): User {
        return userRepository.saveAndFlush(user)
    }
}
