package com.guild.kotlin.adventurer.functional

import com.guild.kotlin.adventurer.controller.impl.JobControllerImpl
import com.guild.kotlin.adventurer.controller.impl.ReportControllerImpl
import com.guild.kotlin.adventurer.controller.impl.UserControllerImpl
import com.guild.kotlin.adventurer.entities.Job
import com.guild.kotlin.adventurer.entities.Report
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.JobRepository
import com.guild.kotlin.adventurer.repo.ReportRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension::class, MockitoExtension::class)
class AddReport {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private val webTestClient: WebTestClient? = null

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private var jobControllerImpl: JobControllerImpl? = null

    @Autowired
    private var jobRepository: JobRepository? = null

    @Autowired
    private var reportControllerImpl: ReportControllerImpl? = null

    @Autowired
    private var reportRepository: ReportRepository? = null


    @Autowired
    private var userControllerImpl: UserControllerImpl? = null

    private fun createNewUser(): User {
        val user = User()
        user.id = 1000
        user.login = "test"
        user.password = "{noop}test"
        user.email = "test"
        user.balance = BigDecimal(1000)
        return user
    }

    private fun createNewReport(): Report {
        val report = Report()
        report.id = 1000
        report.authorId = 1
        report.jobId = 554
        report.text = "test"
        return report
    }

    private fun createNewAdventurer(): User {
        val user = User()
        user.id = 1001
        user.login = "test"
        user.password = "{noop}test"
        user.email = "test"
        user.status = "ADVENTURER"
        user.balance = BigDecimal(1000)
        return user
    }

    private fun createNewJob(): Job {
        val job = Job()
        job.id = 554
        job.customerId = 1
        job.adventurerId = 1
        job.title = "test"
        job.description = "test"
        job.location = "test"
        job.status = "test"
        job.reward = BigDecimal.valueOf(100.55)
        return job
    }


    @Test
    fun add_new_report() {
        val newUser = createNewUser()
        val newAdventurer = createNewAdventurer()
        mockMvc.post(
            "/rest/user/register",
            newUser
        )
        mockMvc.post(
            "/rest/user/register",
            newAdventurer
        )
        val token: MvcResult = mockMvc.post(
            "/rest/user/authenticate",
            newUser
        ).andReturn()

        val newJob = createNewJobs()

        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/jobs", newJob)
                .header("Authentication", "Bearer $token")
        )
            .andReturn()

        val SavedJob = newJob!!.id?.let { jobRepository!!.findById(it) }!!.get()

        Assertions.assertThat(SavedJob.id).isEqualTo(newJob.id)
        Assertions.assertThat(SavedJob.title).isEqualTo(newJob.title)
        Assertions.assertThat(SavedJob.description).isEqualTo(newJob.description)
        Assertions.assertThat(SavedJob.reward).isEqualTo(newJob.reward)
        Assertions.assertThat(SavedJob.status).isEqualTo(newJob.status)
        Assertions.assertThat(SavedJob.location).isEqualTo(newJob.location)

        val newReport = createNewReport()

        mockMvc.perform(
            MockMvcRequestBuilders.post("/rest/report", newReport)
                .header("Authentication", "Bearer $token")
        )
            .andReturn()

        val SavedReport = newReport!!.id?.let { reportRepository!!.findById(it) }!!.get()

        Assertions.assertThat(SavedReport.id).isEqualTo(newReport.id)
        Assertions.assertThat(SavedReport.authorId).isEqualTo(newReport.authorId)
        Assertions.assertThat(SavedReport.jobId).isEqualTo(newReport.jobId)
        Assertions.assertThat(SavedReport.text).isEqualTo(newReport.text)
    }

    private fun createLoginRequest(): User {
        val email = UUID.randomUUID().toString() + "@mail.ru"
        val login = UUID.randomUUID().toString()
        return User(
            login = login,
            email = email,
            password = "password"
        )
    }

//        @Test
//    fun allowed() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
//        val request = MockMvcRequestBuilders.get("/rest")
//        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
//    }
//
//    @Test
//    fun allowed_to_api_register() {
//        val request = MockMvcRequestBuilders.post("/rest/user/register", createLoginRequest())
//        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
//    }
//    @Test
//    fun allowed_api_authenticate() {
//        val request = MockMvcRequestBuilders.post("/rest/user/authenticate")
//        val response = mockMvc.perform(request
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(createLoginRequest().toString())).andExpect(MockMvcResultMatchers.status().isOk)
//    }


    private fun createNewReports(): Report {
        val report = createNewReport()
        reportControllerImpl!!.save(report)
        return report
    }

    private fun createNewJobs(): Job{
        val job = createNewJob()
        jobControllerImpl!!.save(job)
        return job
    }
}
