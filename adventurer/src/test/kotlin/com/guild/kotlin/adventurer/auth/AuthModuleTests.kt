package com.guild.kotlin.adventurer.auth

import com.guild.kotlin.adventurer.entities.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension::class, MockitoExtension::class)
class AuthModuleTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private val webTestClient: WebTestClient? = null

    @Autowired
    private lateinit var context: WebApplicationContext

    @Test
    fun forbidden_to_api_reviews() {
        val request = MockMvcRequestBuilders.get("/rest/review")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_report() {
        val request = MockMvcRequestBuilders.get("/rest/report")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_users() {
        val request = MockMvcRequestBuilders.get("/rest/users")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_adventurer() {
        val request = MockMvcRequestBuilders.get("/rest/adventurer")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_group() {
        val request = MockMvcRequestBuilders.get("/rest/group")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_image() {
        val request = MockMvcRequestBuilders.get("/rest/images")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun forbidden_to_api_jobs() {
        val request = MockMvcRequestBuilders.get("/rest/jobs")
        val response = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden)
    }

//    @Test
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

    private fun createLoginRequest(): User {
        val email = UUID.randomUUID().toString() + "@mail.ru"
        val login = UUID.randomUUID().toString()
        return User(
            login = login,
            email = email,
            password = "password"
        )
    }

//    companion object {
//        @AfterAll
//        fun cleanupData(@Autowired userRepository: UserRepository) {
//            userRepository.deleteAll()
//        }
//    }
}
