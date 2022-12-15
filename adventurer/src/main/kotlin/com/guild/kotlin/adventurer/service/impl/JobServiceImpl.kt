package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.repo.JobRepository
import com.guild.kotlin.adventurer.service.IPageService
import com.guild.kotlin.adventurer.service.IService
import org.json.JSONException
import org.json.JSONObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
@Service
class JobServiceImpl(private val jobRepository: JobRepository): IService<Job>, IPageService<Job> {

    override fun findAll(pageable: Pageable?): Page<Job> {
        return jobRepository.findAll(pageable!!)
    }

    override fun findAll(): Collection<Job> {
        return jobRepository.findAll()
    }

    override fun findById(id: Long?): Optional<Job> {
        return jobRepository.findById(id!!)
    }

    override fun deleteById(id: Long?): String? {
        val jsonObject = JSONObject()
        try {
            jobRepository.deleteById(id!!)
            jsonObject.put("message", "Job deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }

    override fun saveOrUpdate(job: Job): Job {
        return jobRepository.saveAndFlush(job)
    }

//    override fun findAll(pageable: Pageable?, searchText: String?): Page<Job> {
//        return jobRepository.findAllJobs(pageable,  searchText)
//    }
}