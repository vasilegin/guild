package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.repo.JobRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.util.function.Consumer

@DataJpaTest
@ExtendWith(SpringExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobTest {

    @Autowired
    private val jobRepository: JobRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(jobRepository).isNotNull()
    }

    @Test
    fun job_customer_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.customerId = null
        })
    }


    @Test
    fun job_title_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.title = null
        })
    }

    @Test
    fun job_description_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.description = null
        })
    }

    @Test
    fun job_location_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.location = null
        })
    }

    @Test
    fun job_reward_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.reward = null
        })
    }

    @Test
    fun job_status_null() {
        jobTestHelper(Consumer<Job> {job ->
            job.status = null
        })
    }

    @Test
    fun job_customerId_notExists() {
        jobTestHelper {job ->
            job.customerId = 1000L
        }
    }

    @Test
    fun job_adventurerId_notExists() {
        jobTestHelper {job ->
            job.adventurerId = 1000L
        }
    }

    @Test
    fun job_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val job: Job = createJob()
            jobRepository?.save(job)
        }
    }

    private fun jobTestHelper(func: Consumer<Job>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val job: Job = createJob()
            func.accept(job)
            jobRepository?.saveAndFlush(job)
        }
    }


    private fun createJob(): Job {
        val job = Job()
        job.customerId = 1
        job.title = "test"
        job.description = "test"
        job.location = "test"
        job.status = "test"
        job.reward = BigDecimal(100)
        return job
    }
}