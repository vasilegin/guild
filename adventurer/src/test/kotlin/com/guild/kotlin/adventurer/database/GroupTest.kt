package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Adventurer
import com.guild.kotlin.adventurer.entities.Group
import com.guild.kotlin.adventurer.repo.AdventurerRepository
import com.guild.kotlin.adventurer.repo.GroupRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GroupTest {

    @Autowired
    private val groupRepository: GroupRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(groupRepository).isNotNull()
    }

    @Test
    fun group_name_null() {
        groupTestHelper(Consumer<Group> {group ->
            group.name = null
        })
    }

    @Test
    fun group_active_null() {
        groupTestHelper(Consumer<Group> {group ->
            group.active = null
        })
    }

    @Test
    fun group_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val group: Group = createGroup()
            groupRepository?.save(group)
        }
    }


    private fun groupTestHelper(func: Consumer<Group>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val group: Group = createGroup()
            func.accept(group)
            groupRepository?.save(group)
        }
    }


    private fun createGroup(): Group {
        val group = Group()
        group.name = "test"
        group.active = false
        return group
    }
}