package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Participate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ParticipateRepository : JpaRepository<Participate, Long>, JpaSpecificationExecutor<Participate> {
}