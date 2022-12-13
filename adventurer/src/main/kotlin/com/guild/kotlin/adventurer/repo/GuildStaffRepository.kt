package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.GuildStaff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface GuildStaffRepository : JpaRepository<GuildStaff, Long>, JpaSpecificationExecutor<GuildStaff> {
}