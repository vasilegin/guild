package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Participate
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.ParticipateRepository
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
class ParticipateTest {

    @Autowired
    private val participateRepository: ParticipateRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(participateRepository).isNotNull()
    }

    @Test
    fun participate_role_null() {
        participateTestHelper(Consumer<Participate> { participate ->
            participate.role = null
        })
    }

    @Test
    fun participate_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val participate: Participate = createParticipate()
            participateRepository?.save(participate)
        }
    }

    private fun participateTestHelper(func: Consumer<Participate>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val participate: Participate = createParticipate()
            func.accept(participate)
            participateRepository?.saveAndFlush(participate)
        }
    }


    private fun createParticipate(): Participate {
        val participate = Participate()
        participate.role = "test"
        return participate
    }
}