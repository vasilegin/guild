package com.guild.kotlin.adventurer.repo

import com.guild.kotlin.adventurer.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RoleRepository : JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    fun findByName(name: String?): Role
}