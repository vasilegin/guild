package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.Review
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.ReviewRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IService
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ReviewServiceImpl(private val reviewRepository: ReviewRepository): IService<Review> {

    override fun saveOrUpdate(review: Review): Review {
        return reviewRepository.saveAndFlush(review)
    }

    override fun findAll(): Collection<Review>? {
        return reviewRepository.findAll()
    }

    override fun findById(id: Long?): Optional<Review>? {
        return reviewRepository.findById(id!!)
    }

    override fun deleteById(id: Long?): String? {
        val jsonObject = JSONObject()
        try {
            reviewRepository.deleteById(id!!)
            jsonObject.put("message", "Review deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }

}