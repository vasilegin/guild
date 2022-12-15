package com.guild.kotlin.adventurer.controller.impl


import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.service.IPageService
import com.guild.kotlin.adventurer.service.IService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/jobs")
//@CrossOrigin(origins = ["http://localhost:3000"])
class JobControllerImpl(private val jobService: IService<Job>, private val jobPageService: IPageService<Job>) : Resource<Job> {


    override fun findAll(@RequestParam(defaultValue = "0") page: Int,
                         @RequestParam(defaultValue = "10") size: Int,
                         @RequestParam(defaultValue = "title") sort: String?,
                         @RequestParam(defaultValue = "title") sortDir: String?): ResponseEntity<Page<Job>> {
        return ResponseEntity.ok(jobPageService.findAll(
            PageRequest.of(
                page, size,
                if (sortDir.equals("asc", ignoreCase = true)) Sort.by(sort).ascending() else Sort.by(sort)
                    .descending()
            )
        ))
    }

//    override fun findAll(pageable: Pageable?, @PathVariable sortBy: String?): ResponseEntity<Page<Job>> {
//        return
//    }

    override fun findById(@PathVariable id: Long?): ResponseEntity<Optional<Job>> {
        return ResponseEntity.ok(jobService.findById(id))
    }

    override fun save(job: Job): ResponseEntity<Job?> {
        return ResponseEntity.ok(jobService.saveOrUpdate(job))
    }

    override fun update(job: Job): ResponseEntity<Job?> {
        return ResponseEntity.ok(jobService.saveOrUpdate(job))
    }

    override fun deleteById(@PathVariable id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(jobService.deleteById(id))
    }
}