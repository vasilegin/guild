package com.guild.kotlin.adventurer.controller.impl


import com.guild.kotlin.adventurer.controller.Resource
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.service.IPageService
import com.guild.kotlin.adventurer.service.IService
import com.guild.kotlin.adventurer.service.impl.UserServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:3000"])
class UsersControllerImpl(private val userService: UserServiceImpl) : Resource<User> {
    override fun findAll(
        pageNumber: Int,
        pageSize: Int,
        sortBy: String?,
        sortDir: String?
    ): ResponseEntity<Page<User>> {
        TODO("Not yet implemented")
    }

    override fun findById(@PathVariable id: Long?): ResponseEntity<Optional<User>> {
        System.out.println(userService.findById(id));
        return ResponseEntity.ok(userService.findById(id))
    }

    override fun save(user: User): ResponseEntity<User?> {
        return ResponseEntity.ok(userService.saveOrUpdate(user))
    }

    override fun update(user: User): ResponseEntity<User?> {
        System.out.println("test");
        System.out.println(user);
        return ResponseEntity.ok(userService.saveOrUpdate(user))
    }

    override fun deleteById(@PathVariable id: Long?): ResponseEntity<String?> {
        return ResponseEntity.ok(userService.deleteById(id))
    }
}