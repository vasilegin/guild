package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface JobRepository : JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

//    fun findById(id: Long?): Optional<Job>
//    fun findAllJobs(pageable: Pageable?, searchText: String?): Page<Job>
//    fun findAll(pageable: Pageable?): Page<Job>
}