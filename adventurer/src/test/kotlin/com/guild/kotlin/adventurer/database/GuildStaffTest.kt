package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.GuildStaff
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GuildStaffTest {

    @Autowired
    private val guildStaffRepository: GuildStaffRepository? = null

    @Test
    fun contextLoads() {
        assertThat(guildStaffRepository).isNotNull()
    }

    @Test
    fun guildStaff_position_null() {
        guildStaffTestHelper(Consumer<GuildStaff> {guildStaff ->
            guildStaff.position = null

        })
    }

    @Test
    fun guildStaff_success() {
        Assertions.assertDoesNotThrow {
            val guildStaff: GuildStaff = createGuildStaff()
            guildStaffRepository?.save(guildStaff)
        }
    }

    private fun guildStaffTestHelper(func: Consumer<GuildStaff>) {
        Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val guildStaff: GuildStaff = createGuildStaff()
            func.accept(guildStaff)
            guildStaffRepository?.save(guildStaff)
        }
    }


    private fun createGuildStaff(): GuildStaff {
        val guildStaff = GuildStaff()
        guildStaff.position = "test"
        return guildStaff
    }
}