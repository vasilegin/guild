package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.User
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.math.BigDecimal
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTest {

    @Autowired
    private val userRepository: UserRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(userRepository).isNotNull()
    }

    @Test
    fun user_login_null() {
        userTestHelper(Consumer<User> { user ->
            user.login = null
        })
    }

    @Test
    fun user_password_null() {
        userTestHelper(Consumer<User> { user ->
            user.password = null
        })
    }

    @Test
    fun user_email_null() {
        userTestHelper(Consumer<User> { user ->
            user.email = null
        })
    }

    @Test
    fun user_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val user: User = createUser()
            userRepository?.save(user)
        }
    }

    private fun userTestHelper(func: Consumer<User>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val user: User = createUser()
            func.accept(user)
            userRepository?.saveAndFlush(user)
        }
    }


    private fun createUser(): User {
        val user = User()
        user.login = "test"
        user.password = "test"
        user.email = "test"
        return user
    }
}