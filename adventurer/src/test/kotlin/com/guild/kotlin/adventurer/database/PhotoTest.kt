package com.guild.kotlin.adventurer.database

import com.guild.kotlin.adventurer.entities.Photo
import com.guild.kotlin.adventurer.repo.GuildStaffRepository
import com.guild.kotlin.adventurer.repo.PhotoRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import java.util.function.Consumer

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PhotoTest {

    @Autowired
    private val photoRepository: PhotoRepository? = null

    @Test
    fun contextLoads() {
        Assertions.assertThat(photoRepository).isNotNull()
    }
    @Test
    fun photo_location_null() {
        photoTestHelper(Consumer<Photo> { photo ->
            photo.location = null
        })
    }

    @Test
    fun photo_jobId_notExists() {
        photoTestHelper {photo ->
            photo.jobId = 1000L
        }
    }

    @Test
    fun photo_success() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow {
            val photo: Photo = createPhoto()
            photoRepository?.save(photo)
        }
    }

    private fun photoTestHelper(func: Consumer<Photo>) {
        org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            val photo: Photo = createPhoto()
            func.accept(photo)
            photoRepository?.saveAndFlush(photo)
        }
    }


    private fun createPhoto(): Photo {
        val photo = Photo()
        photo.location = "test"
        return photo
    }
}