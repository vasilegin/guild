package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.Review
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
@CrossOrigin(origins = ["http://localhost:3000"])
class ReviewControllerImpl(private val reviewServiceImpl: IService<Review>): Resource<Review> {
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
        return ResponseEntity.ok(reviewServiceImpl.saveOrUpdate(review))
    }
}