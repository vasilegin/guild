package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.Review
import com.guild.kotlin.adventurer.service.IService
import com.guild.kotlin.adventurer.service.impl.ReportServiceImpl
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = ["http://localhost:3000"])
class ReportControllerImpl(private val reportServiceImpl: IService<Report>): Resource<Report> {
    override fun findAll(
        pageNumber: Int,
        pageSize: Int,
        sortBy: String?,
        sortDir: String?
    ): ResponseEntity<Page<Report>> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): ResponseEntity<Optional<Report>> {
        return ResponseEntity.ok(reportServiceImpl.findById(id))
    }

    override fun deleteById(id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(reportServiceImpl.deleteById(id))
    }

    override fun update(report: Report): ResponseEntity<Report?> {
        return ResponseEntity.ok(reportServiceImpl.saveOrUpdate(report))
    }

    override fun save(report: Report): Any {
        return ResponseEntity.ok(reportServiceImpl.saveOrUpdate(report))
    }
}