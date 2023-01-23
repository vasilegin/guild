package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Adventurer
import com.guild.kotlin.adventurer.entities.GuildStaff
import com.guild.kotlin.adventurer.repo.AdventurerRepository
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdventurerTest {

    @Autowired
    private val adventurerRepository: AdventurerRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(adventurerRepository).isNotNull()
    }

    @Test
    fun adventurer_rank_null() {
        adventurerTestHelper(Consumer<Adventurer> {adventurer ->
            adventurer.rank = null
        })
    }

    @Test
    fun adventurer_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val adventurer: Adventurer = createGuildStaff()
            adventurerRepository?.save(adventurer)
        }
    }

    private fun adventurerTestHelper(func: Consumer<Adventurer>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val adventurer: Adventurer = createGuildStaff()
            func.accept(adventurer)
            adventurerRepository?.save(adventurer)
        }
    }


    private fun createGuildStaff(): Adventurer {
        val adventurer = Adventurer()
        adventurer.rank = "test"
        adventurer.role = "test"
        adventurer.weapon = "test"
        return adventurer
    }
}