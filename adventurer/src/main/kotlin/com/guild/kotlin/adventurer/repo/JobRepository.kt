package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface JobRepository : JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    fun findAllByStatus(pageable: Pageable?, status: String?) : Page<Job>?
    fun findAllByStatusAndTitleContaining(pageable: Pageable?, status: String?, title: String?) : Page<Job>?

    fun findAllByStatusAndRank(pageable: Pageable?, status: String?, rank: String?) : Page<Job>?

    fun findAllByStatusAndTitleContainingAndRank(pageable: Pageable?, status: String?, title: String?, rank: String?) : Page<Job>?
    fun findAllByTitleContaining(pageable: Pageable?, title: String?): Page<Job>?

//    fun findById(id: Long?): Optional<Job>
//    fun findAll(pageable: Pageable?): Page<Job>

//    @Query("SELECT Job FROM Job j WHERE j.title LIKE %:searchText% ORDER BY j.id ASC")
//    fun findAllJobs(pageable: Pageable?, @Param("searchText") searchText: String?): Page<Job>?
}