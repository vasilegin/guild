package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.Photo
import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.Review
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.PhotoRepository
import com.guild.kotlin.adventurer.repo.ReviewRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IService
import org.json.JSONException
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.util.*

@Service
class PhotoServiceImpl(private val photoRepository: PhotoRepository) {

    fun saveOrUpdate(photo: Photo): Photo {
        return photoRepository.saveAndFlush(photo)
    }

    fun deleteById(id: Long?): String? {
        val jsonObject = JSONObject()
        try {
            photoRepository.deleteById(id!!)
            jsonObject.put("message", "Photo deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }

}