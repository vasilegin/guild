package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByLogin(login: String?): User?

}