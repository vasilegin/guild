package com.guild.kotlin.adventurer.controller.impl


import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.service.IPageService
import com.guild.kotlin.adventurer.service.IService
import com.guild.kotlin.adventurer.service.impl.AdventurerUserServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/adventurer")
@CrossOrigin(origins = ["http://localhost:3000"])
class AdventurersControllerImpl(private val userPageService: AdventurerUserServiceImpl) : Resource<User>  {

    @GetMapping
    override fun findAll(@RequestParam(defaultValue = "0") page: Int,
                         @RequestParam(defaultValue = "10") size: Int,
                         @RequestParam(defaultValue = "firstname") sort: String?,
                         @RequestParam(defaultValue = "firstname") sortDir: String?): ResponseEntity<Page<User>> {
        return ResponseEntity.ok(userPageService.findAllAdv(
            PageRequest.of(
                page, size,
                if (sortDir.equals("firstname", ignoreCase = true)) Sort.by(sort).ascending() else Sort.by(sort)
                    .descending()
            )
        ))
    }

    @GetMapping("/search")
    fun findAll(pageable: Pageable?, @RequestParam login: String?): ResponseEntity<Page<User>> {
        if (login != null){
            return ResponseEntity.ok(userPageService.findAllByLoginContainingAndAdventurerIsNotNull(pageable, login))
        }
        return ResponseEntity.ok(userPageService.findAllAdv(pageable))
    }

    @GetMapping("/test")
    fun findAll(pageable: Pageable, @RequestParam login: String?, @RequestParam status: String): ResponseEntity<Page<User>> {
        if (login != null){
            return ResponseEntity.ok(userPageService.findForTestByLogin(pageable, login, status))
        }
        return ResponseEntity.ok(userPageService.findForTestByStatus(pageable, status))
    }


    @GetMapping("{id}")
    override fun findById(@PathVariable id: Long?): ResponseEntity<Optional<User>> {
        return ResponseEntity.ok(userPageService.findById(id))
    }

    override fun save(user: User): ResponseEntity<User?> {
        return ResponseEntity.ok(userPageService.saveOrUpdate(user))
    }

    override fun update(user: User): ResponseEntity<User?> {
        return ResponseEntity.ok(userPageService.saveOrUpdate(user))
    }

    override fun deleteById(@PathVariable id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(userPageService.deleteById(id))
    }
}