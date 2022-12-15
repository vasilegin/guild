package com.guild.kotlin.adventurer.repo

import com.guild.kotlin.adventurer.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AdventurerUserRepositoty : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

//    fun findById(id: Long?): Optional<User>
//    fun findAllUsers(pageable: Pageable?, @Param("searchText") searchText: String?): Page<User>
//    fun findAll(pageable: Pageable?): Page<User>
}