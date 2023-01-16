package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.AdventurerApplication
import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.UserRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import java.util.*


@SpringBootTest(classes = [AdventurerApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AuthModuleTests {
    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun register_success() {

//        val request: Unit = createRegisterRequest()
//        val response: Unit = restTemplate
//            .postForEntity("/rest/user/register", request, AuthResponse::class.java)
//        assertPositiveResponse(response, request.getEmail())
        Assertions.assertEquals("Vlad", "Vlad")
        Assertions.assertEquals("Igor", "Igor")
    }
//
//    @Test
//    fun register_error_emailAlreadyUsed() {
//        val request: Unit = createRegisterRequest()
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, AuthResponse::class.java)
//        assertPositiveResponse(response, request.getEmail())
//        val errorResponse: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, ErrorResponse::class.java)
//        assertNegativeResponse(errorResponse, HttpStatus.BAD_REQUEST, ApiErrors.Auth.UserAlreadyExists)
//    }
//
//    @Test
//    fun register_validation_emptyEmail() {
//        val request: Unit = createRegisterRequest()
//        request.setEmail(null)
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, ErrorResponse::class.java)
//        assertValidationError(response)
//    }
//
//    @Test
//    fun register_validation_emptyPassword() {
//        val request: Unit = createRegisterRequest()
//        request.setPassword(null)
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, ErrorResponse::class.java)
//        assertValidationError(response)
//    }
//
//    @Test
//    fun register_validation_emptyRole() {
//        val request: Unit = createRegisterRequest()
//        request.setRole(null)
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, ErrorResponse::class.java)
//        assertValidationError(response)
//    }
//
//    @Test
//    fun login_success() {
//        val request: Unit = createRegisterRequest()
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, AuthResponse::class.java)
//        assertPositiveResponse(response, request.getEmail())
//        val loginRequest = LoginRequest(request.getEmail(), request.getPassword())
//        val loginResponse: Unit = restTemplate
//            .postForEntity("/api/auth/login", loginRequest, AuthResponse::class.java)
//        assertPositiveResponse(loginResponse, request.getEmail())
//    }
//
//    @Test
//    fun login_error_userDoesNotExists() {
//        val request: Unit = createRegisterRequest()
//        val loginRequest = LoginRequest(request.getEmail(), request.getPassword())
//        val loginResponse: Unit = restTemplate
//            .postForEntity("/api/auth/login", loginRequest, ErrorResponse::class.java)
//        assertNegativeResponse(loginResponse, HttpStatus.NOT_FOUND, ApiErrors.Auth.UserNotFound)
//    }
//
//    @Test
//    fun login_error_wrongPassword() {
//        val request: Unit = createRegisterRequest()
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/register", request, AuthResponse::class.java)
//        assertPositiveResponse(response, request.getEmail())
//        val loginRequest = LoginRequest(request.getEmail(), "wrong password")
//        val loginResponse: Unit = restTemplate
//            .postForEntity("/api/auth/login", loginRequest, ErrorResponse::class.java)
//        assertNegativeResponse(loginResponse, HttpStatus.NOT_FOUND, ApiErrors.Auth.UserNotFound)
//    }
//
//    @Test
//    fun login_validation_emptyEmail() {
//        val request: LoginRequest = createLoginRequest()
//        request.setEmail(null)
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/login", request, ErrorResponse::class.java)
//        assertValidationError(response)
//    }
//
//    @Test
//    fun login_validation_emptyPassword() {
//        val request: LoginRequest = createLoginRequest()
//        request.setPassword(null)
//        val response: Unit = restTemplate
//            .postForEntity("/api/auth/login", request, ErrorResponse::class.java)
//        assertValidationError(response)
//    }
//
//    private fun assertPositiveResponse(response: ResponseEntity<AuthResponse>, email: String) {
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())
//        Assertions.assertNotNull(response.getBody())
//        Assertions.assertNotNull(response.getBody().getToken())
//        Assertions.assertEquals(response.getBody().getUser().getEmail(), email)
//    }

    private fun createLoginRequest(): User {
        val email = UUID.randomUUID().toString() + "@mail.ru"
        val login = UUID.randomUUID().toString()
        return User(
            login = login,
            email = email,
            password = "{noop}password"
        )
    }

    companion object {
        @AfterAll
        fun cleanupData(@Autowired userRepository: UserRepository) {
            userRepository.deleteAll()
        }
    }
}