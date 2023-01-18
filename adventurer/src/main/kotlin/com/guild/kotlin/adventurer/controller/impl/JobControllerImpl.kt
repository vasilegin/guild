package com.guild.kotlin.adventurer.controller.impl


import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import com.guild.kotlin.adventurer.service.IPageService
import com.guild.kotlin.adventurer.service.IService
import com.guild.kotlin.adventurer.service.impl.JobServiceImpl
import com.guild.kotlin.adventurer.service.impl.UserServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/jobs")
//@CrossOrigin(allowedHeaders = ["Authorization", "Origin"])
@CrossOrigin(origins = ["http://localhost:3000"])
class JobControllerImpl(private val jobService: IService<Job>, private val jobPageService: IPageService<Job>, private val jobServiceImpl: JobServiceImpl, private val  userRepository: UserRepository) : Resource<Job> {

    @GetMapping("/adventurer")
    fun findAll(pageable: Pageable,
                @RequestParam id: Long ,
                @RequestParam status: String): ResponseEntity<Page<Job>> {
    return ResponseEntity.ok(jobServiceImpl.findAllJobForAdventurer(pageable, id, status))
    }

    @GetMapping("/customer")
    fun findAll(pageable: Pageable,
                @RequestParam id: Long): ResponseEntity<Page<Job>> {
        return ResponseEntity.ok(jobServiceImpl.findAllJobForCustomer(pageable, id))
    }

    override fun findAll(@RequestParam(defaultValue = "1") page: Int,
                         @RequestParam(defaultValue = "10") size: Int,
                         @RequestParam(defaultValue = "title") sort: String?,
                         @RequestParam(defaultValue = "title") sortDir: String?
    ): ResponseEntity<Page<Job>> {
        return ResponseEntity.ok(jobPageService.findAll(
            PageRequest.of(
                page, size,
                if (sortDir.equals("asc", ignoreCase = true)) Sort.by(sort).ascending() else Sort.by(sort)
                    .descending()
            )
        ))
    }

    @GetMapping("/search")
    fun findAll(pageable: Pageable,
                @RequestParam status: String? ,
                @RequestParam title: String?,
                @RequestParam rank: String?): ResponseEntity<Page<Job>> {
        if (title == null){
            if (rank == null) {
                return ResponseEntity.ok(jobServiceImpl.findAllByStatus(pageable, status))
            } else {
                return ResponseEntity.ok(jobServiceImpl.findAllByStatusAndRank(pageable, status, rank))
            }
        }
        else{
            if (rank == null) {
                return ResponseEntity.ok(jobServiceImpl.findAll(pageable, status, title))
            }else{
                return ResponseEntity.ok(jobServiceImpl.findAll(pageable, status, title, rank))
            }
        }
    }

    override fun findById(@PathVariable id: Long?): ResponseEntity<Optional<Job>> {
        return ResponseEntity.ok(jobService.findById(id))
    }

    override fun save(job: Job): Any {
        if (userRepository.findById(job.customerId!!).get().balance!! < job.reward) {
            return ResponseEntity.notFound()
        }
        else {
            var user: User = userRepository.findById(job.customerId!!).get()
            user!!.balance = user!!.balance!! - job.reward!!
            userRepository.saveAndFlush(user)
            return ResponseEntity.ok(jobService.saveOrUpdate(job))
        }
    }

    override fun update(job: Job): ResponseEntity<Job?> {
        return ResponseEntity.ok(jobService.saveOrUpdate(job))
    }

    override fun deleteById(@PathVariable id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(jobService.deleteById(id))
    }
}