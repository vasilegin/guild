package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PhotoRepository : JpaRepository<Photo, Long>, JpaSpecificationExecutor<Photo> {
}