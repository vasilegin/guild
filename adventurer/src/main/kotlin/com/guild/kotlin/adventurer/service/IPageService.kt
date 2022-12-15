package com.guild.kotlin.adventurer.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IPageService<T> {

//    fun findAll(pageable: Pageable?, searchText: String?): Page<T>?
    fun findAll(pageable: Pageable?): Page<T>?
}
