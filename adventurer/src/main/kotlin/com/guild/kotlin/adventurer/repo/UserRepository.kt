package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByLogin(login: String?): User?

    fun findAllByAdventurerIsNotNull() : List<User>?

    fun findAllByAdventurerIsNotNull(pageable: Pageable) : Page<User>?
    fun findAllByLoginContainingAndAdventurerIsNotNull(pageable: Pageable?, login: String) : Page<User>?
    fun findByIdAndAdventurerIsNotNull(id: Long): Optional<User>

//    fun findAllByAdventurerIsNotNull() : List<User>?

//    @Query("SELECT User FROM User u WHERE u.surname LIKE %:searchText%  or u.firstname LIKE %:searchText% ORDER BY u.id ASC")
//    fun findAllAdventurers(pageable: Pageable?, @Param("searchText") searchText: String?): Page<User>
}