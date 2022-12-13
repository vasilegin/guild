package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface JobRepository : JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
}