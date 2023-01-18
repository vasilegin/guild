package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.entities.Photo
import com.guild.kotlin.adventurer.service.impl.PhotoServiceImpl
import com.guild.kotlin.adventurer.utils.Pictures
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.*
import javax.xml.bind.DatatypeConverter
import kotlin.io.path.Path
import kotlin.io.path.writeBytes


@RestController
@RequestMapping("/images")
//@CrossOrigin(origins = ["http://localhost:3000"])
class ImagesControllerImpl(private val photoServiceImpl: PhotoServiceImpl){

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun save(@RequestBody pictures: Pictures): Any {
        pictures.pictures!!.forEach {
            val data: String = it.split(",").get(0)
            val dataName: String = data.split(";").get(1)
            val name: String = dataName.split("=").get(1)
            val base64Image: String = it.split(",").get(1)
            val imageBytes = DatatypeConverter.parseBase64Binary(base64Image)
            val path = Path(System.getProperty("user.dir") + "/client/src/assets/images/" + pictures.userName + "/" + pictures.directory + "/" + name);
            val specPath = "images/" + pictures.userName + "/"  + pictures.directory + "/" + name
            path.writeBytes(imageBytes)

            val photo: Photo = Photo(location = specPath, jobId= pictures.jobId,  id = 0)
            photoServiceImpl.saveOrUpdate(photo)
        }
        return ResponseEntity.ok()
    }
}