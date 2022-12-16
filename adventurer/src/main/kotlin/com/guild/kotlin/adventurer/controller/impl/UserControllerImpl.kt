package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.config.JwtTokenProvider
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.RoleRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.utils.ConstantUtils
import org.json.JSONException
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = ["http://localhost:3000"])
class UserControllerImpl(private val authenticationManager: AuthenticationManager,
                         private val tokenProvider: JwtTokenProvider,
                         private val roleRepository: RoleRepository,
                         private val userRepository: UserRepository) {

    @PostMapping(value = ["/authenticate"])
    fun authenticate(@RequestBody user: User): ResponseEntity<String?>? {
        val jsonObject = JSONObject()
        System.out.println(user);
        try {
            val authentication: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(user.login, user.password))
            if (authentication.isAuthenticated()) {
                val login: String = user.login!!
                jsonObject.put("name", authentication.getName())
                jsonObject.put("authorities", authentication.getAuthorities())
                val user = userRepository.findByLogin(login)!!
                val role = user.role!!.name!!
                jsonObject.put("token", tokenProvider.createToken(login, role))
                return ResponseEntity(jsonObject.toString(), HttpStatus.OK)
            }
        } catch (e: JSONException) {
            try {
                jsonObject.put("exception", e.message)
            } catch (e1: JSONException) {
                e1.printStackTrace()
            }
            return ResponseEntity(jsonObject.toString(), HttpStatus.UNAUTHORIZED)
        }
        return null
    }

    @PostMapping(value = ["/register"])
    fun register(@RequestBody user: User): ResponseEntity<String?>? {
        val jsonObject = JSONObject()
        try {
            user.password = BCryptPasswordEncoder().encode(user.password)
            user.role = roleRepository.findByName(ConstantUtils.USER.toString())
            val savedUser = userRepository.saveAndFlush(user)
            jsonObject.put("message", savedUser.login + " saved succesfully")
            return ResponseEntity(jsonObject.toString(), HttpStatus.OK)
        } catch (e: JSONException) {
            try {
                jsonObject.put("exception", e.message)
            } catch (e1: JSONException) {
                e1.printStackTrace()
            }
            return ResponseEntity(jsonObject.toString(), HttpStatus.UNAUTHORIZED)
        }
        return null
    }

    @GetMapping("/str")
    fun hellow(): ResponseEntity<String> {
        return ResponseEntity("hellow", HttpStatus.OK)
    }
}