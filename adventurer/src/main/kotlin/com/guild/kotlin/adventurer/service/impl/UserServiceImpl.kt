package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IService
import org.json.JSONObject
import org.json.JSONException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : IService<User> {

    override fun findAll(): Collection<User> {
        return userRepository.findAll()
    }

    override fun findById(id: Long?): Optional<User> {
        return userRepository.findById(id!!)
    }

    override fun saveOrUpdate(user: User): User {
        return userRepository.saveAndFlush(user)
    }

    override fun deleteById(id: Long?): String {
        val jsonObject = JSONObject()
        try {
            userRepository.deleteById(id!!)
            jsonObject.put("message", "User deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }
}