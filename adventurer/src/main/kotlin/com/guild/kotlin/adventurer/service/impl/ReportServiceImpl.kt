package com.guild.kotlin.adventurer.service.impl

import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.ReportRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IService
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ReportServiceImpl(private val reportRepository: ReportRepository) : IService<Report> {

    override fun saveOrUpdate(report: Report): Report {
        return reportRepository.saveAndFlush(report)
    }

    override fun findAll(): Collection<Report>? {
        return reportRepository.findAll()
    }

    override fun findById(id: Long?): Optional<Report>? {
        return reportRepository.findById(id!!)
    }

    override fun deleteById(id: Long?): String? {
        val jsonObject = JSONObject()
        try {
            reportRepository.deleteById(id!!)
            jsonObject.put("message", "Report deleted successfully")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject.toString()
    }
}