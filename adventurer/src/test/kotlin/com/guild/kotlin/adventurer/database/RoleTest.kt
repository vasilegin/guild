package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Role
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.RoleRepository
import com.guild.kotlin.adventurer.utils.ConstantUtils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleTest {

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(roleRepository).isNotNull()
    }
    @Test
    fun role_name_null() {
        roleTestHelper(Consumer<Role> { role ->
            role.name = null
        })
    }

    @Test
    fun role_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val role: Role = createRole()
            roleRepository?.save(role)
        }
    }

    private fun roleTestHelper(func: Consumer<Role>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val role: Role = createRole()
            func.accept(role)
            roleRepository?.saveAndFlush(role)
        }
    }


    private fun createRole(): Role {
        val role = Role()
        role.name = "test"
        return role
    }
}