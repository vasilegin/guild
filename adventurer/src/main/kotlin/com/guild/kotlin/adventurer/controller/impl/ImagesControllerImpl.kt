package com.guild.kotlin.adventurer.controller.impl

import com.guild.kotlin.adventurer.entities.Photo
import com.guild.kotlin.adventurer.service.impl.PhotoServiceImpl
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.*
import javax.xml.bind.DatatypeConverter
import kotlin.io.path.Path
import kotlin.io.path.writeBytes


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = ["http://localhost:3000"])
class ImagesControllerImpl(private val photoServiceImpl: PhotoServiceImpl){

    open class Pictures {
        open var pictures: MutableSet<String>? = null
        open var userName: String? = null
        open var directory: String? = null
        open var jobId: Long? = null
    }
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun save(@RequestBody pictures: Pictures): Any {
        System.out.println("-------------");
        pictures.pictures!!.forEach {
            System.out.println(it);
            val data: String = it.split(",").get(0)
            val dataName: String = data.split(";").get(1)
            val name: String = dataName.split("=").get(1)
            System.out.println(name);
            val base64Image: String = it.split(",").get(1)
            val imageBytes = DatatypeConverter.parseBase64Binary(base64Image)
            val path = Path(System.getProperty("user.dir") + "/client/src/assets/images/" + pictures.userName + "/" + pictures.directory + "/" + name);
            val specPath = "images/" + pictures.userName + "/"  + pictures.directory + "/" + name
            path.writeBytes(imageBytes)

            val photo: Photo = Photo(location = specPath, jobId= pictures.jobId,  id = 0)
            photoServiceImpl.saveOrUpdate(photo)
        }
        System.out.println("-------------");
        return ResponseEntity.ok()
    }
}