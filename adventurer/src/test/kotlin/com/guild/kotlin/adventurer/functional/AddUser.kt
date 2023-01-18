package com.guild.kotlin.adventurer.functional

import com.guild.kotlin.adventurer.controller.impl.JobControllerImpl
import com.guild.kotlin.adventurer.controller.impl.UserControllerImpl
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.JobRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension::class, MockitoExtension::class)
class AddUser {
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
    private var userRepository: UserRepository? = null

    @Autowired
    private var userControllerImpl: UserControllerImpl? = null

    private fun createNewUser(): User {
        val user = User()
        user.id = 555
        user.login = "test"
        user.password = "{noop}test"
        user.email = "test"
        user.balance = BigDecimal.valueOf(100.55)
        return user
    }


    @Test
    fun add_new_user() {
        val newUser = createNewUsers()

        mockMvc.post(
            "/rest/user/register",
            newUser
        )

        val SavedUser = newUser!!.id?.let { userRepository!!.findById(it) }!!.get()

        Assertions.assertThat(SavedUser.id).isEqualTo(newUser.id)
        Assertions.assertThat(SavedUser.login).isEqualTo(newUser.login)
        Assertions.assertThat(SavedUser.password).isEqualTo(newUser.password)
        Assertions.assertThat(SavedUser.email).isEqualTo(newUser.email)
        Assertions.assertThat(SavedUser.balance).isEqualTo(newUser.balance)
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



    private fun createNewUsers(): User{
        val user = createNewUser()
        userControllerImpl!!.register(user)
        return user
    }
}
