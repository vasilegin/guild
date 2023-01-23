package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Review
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.ReviewRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewTest {

    @Autowired
    private val reviewRepository: ReviewRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(reviewRepository).isNotNull()
    }
    @Test
    fun review_text_null() {
        reviewTestHelper(Consumer<Review> { review ->
            review.text = null
        })
    }
    @Test
    fun review_authorId_null() {
        reviewTestHelper(Consumer<Review> { review ->
            review.authorId = null
        })
    }
    @Test
    fun review_jobId_null() {
        reviewTestHelper(Consumer<Review> { review ->
            review.jobId = null
        })
    }

    @Test
    fun review_jobId_notExists() {
        reviewTestHelper {review ->
            review.jobId = 1000L
        }
    }

    @Test
    fun review_authorId_notExists() {
        reviewTestHelper {review ->
            review.authorId = 1000L
        }
    }

    @Test
    fun review_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val review: Review = createReview()
            reviewRepository?.save(review)
        }
    }

    private fun reviewTestHelper(func: Consumer<Review>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val review: Review = createReview()
            func.accept(review)
            reviewRepository?.saveAndFlush(review)
        }
    }


    private fun createReview(): Review {
        val review = Review()
        review.authorId = 1
        review.jobId = 1
        review.text = "test"
        return review
    }
}