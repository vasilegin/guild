package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Adventurer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AdventurerRepository : JpaRepository<Adventurer, Long>, JpaSpecificationExecutor<Adventurer> {
}