package com.guild.kotlin.adventurer.controller

import com.guild.kotlin.adventurer.service.UserService
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HellowController(private val userService: UserService) {

    @GetMapping
    fun Hellow() : ResponseEntity<Any>{
        val users = userService.findAll()
        System.out.println(users.toString())
        return ResponseEntity.ok(users)
    }
}