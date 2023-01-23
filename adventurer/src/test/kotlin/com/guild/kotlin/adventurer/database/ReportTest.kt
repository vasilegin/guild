package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.ReportRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportTest {

    @Autowired
    private val reportRepository: ReportRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(reportRepository).isNotNull()
    }
    @Test
    fun report_text_null() {
        reportTestHelper(Consumer<Report> { report ->
            report.text = null
        })
    }
    @Test
    fun report_authorId_null() {
        reportTestHelper(Consumer<Report> { report ->
            report.authorId = null
        })
    }
    @Test
    fun report_jobId_null() {
        reportTestHelper(Consumer<Report> { report ->
            report.jobId = null
        })
    }

    @Test
    fun report_jobId_notExists() {
        reportTestHelper {report ->
            report.jobId = 1000L
        }
    }

    @Test
    fun report_authorId_notExists() {
        reportTestHelper {report ->
            report.authorId = 1000L
        }
    }

    @Test
    fun report_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val report: Report = createReport()
            reportRepository?.save(report)
        }
    }

    private fun reportTestHelper(func: Consumer<Report>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val report: Report = createReport()
            func.accept(report)
            reportRepository?.saveAndFlush(report)
        }
    }


    private fun createReport(): Report {
        val report = Report()
        report.authorId = 1
        report.jobId = 1
        report.text = "test"
        return report
    }
}