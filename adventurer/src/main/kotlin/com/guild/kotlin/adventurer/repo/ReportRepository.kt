package com.guild.kotlin.adventurer.repo;

import com.guild.kotlin.adventurer.entities.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ReportRepository : JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {
}