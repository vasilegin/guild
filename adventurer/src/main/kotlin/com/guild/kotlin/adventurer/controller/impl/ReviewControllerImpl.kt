package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.Review
import com.guild.kotlin.adventurer.repo.JobRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IService
import com.guild.kotlin.adventurer.service.impl.ReviewServiceImpl
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/review")
//@CrossOrigin(allowedHeaders = ["Authorization", "Origin"])
@CrossOrigin(origins = ["http://localhost:3000"])
class ReviewControllerImpl(private val reviewServiceImpl: IService<Review>,
                           private val jobRepository: JobRepository,
                           private val userRepository: UserRepository): Resource<Review> {

    val Rank = mapOf(
        "F" to 0,
        "E" to 1,
        "D" to  2,
        "B" to 3,
        "A" to 4,
        "A+" to 5,
        "A++" to 6,
        "S" to 7,
        "SS" to 8)

    override fun findAll(
        pageNumber: Int,
        pageSize: Int,
        sortBy: String?,
        sortDir: String?
    ): ResponseEntity<Page<Review>> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): ResponseEntity<Optional<Review>> {
        return ResponseEntity.ok(reviewServiceImpl.findById(id))
    }

    override fun deleteById(id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(reviewServiceImpl.deleteById(id))
    }

    override fun update(review: Review): ResponseEntity<Review?> {
        return ResponseEntity.ok(reviewServiceImpl.saveOrUpdate(review))
    }

    override fun save(review: Review): Any {
        if (review.score?.toInt() == 100){
            var job = jobRepository.findById(review.jobId!!).get()
            var adventurer = userRepository.findById(job.adventurerId!!).get()
            job.status = "Completed"
            adventurer.balance = adventurer.balance?.plus(job.reward!!)
            if (Rank[job.rank]!! > Rank[adventurer.adventurer!!.rank]!!){
                adventurer.adventurer!!.rank = job.rank
            }
            userRepository.saveAndFlush(adventurer)
            jobRepository.saveAndFlush(job)
        }
        return ResponseEntity.ok(reviewServiceImpl.saveOrUpdate(review))
    }
}