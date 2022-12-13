package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface GroupRepository : JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {
}