package com.guild.kotlin.adventurer.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

interface Resource<T> {
//    @GetMapping("/search/{searchText}")
//    fun findAll(pageable: Pageable?, @PathVariable searchText: String?): ResponseEntity<Page<T>>

    @GetMapping
    fun findAll(pageNumber: Int, pageSize: Int, sortBy: String?, sortDir: String?): ResponseEntity<Page<T>>

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long?): ResponseEntity<Optional<T>>

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun save(@RequestBody t: T): ResponseEntity<T?>

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun update(@RequestBody t: T): ResponseEntity<T?>

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: Long?): ResponseEntity<String?>
}