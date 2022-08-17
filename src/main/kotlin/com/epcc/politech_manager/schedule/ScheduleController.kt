package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.*
import com.google.gson.Gson
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@RestController
class ScheduleController(val service: ScheduleService, val userService: UserService) {

    private val root: Path = Paths.get("scheduleFile")

    @GetMapping("/schedule/download")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun downloadFileFromLocal(@RequestHeader("Authorization") auth: String,
                              @RequestBody requestData: ScheduleBO): ResponseEntity<*>? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            createDirectory()
            val scheduleType = requestData.scheduleType.toScheduleType()
            val fileType = requestData.fileType.toFileType()
            service.initData(scheduleData = requestData.subjects, fileType = fileType, scheduleType = scheduleType, degree = requestData.degree, year = requestData.year)
            val fileName = service.createFile()

            val resource = loadFile(fileName)

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename?.toString() + "\"")
                    .body<Any?>(resource)
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/schedule")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody requestData: ScheduleBO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val list = requestData.subjects.joinToString(separator = ";") { it.toString() }
            service.post(
                    ScheduleEntityDTO(
                            list,
                            requestData.scheduleType,
                            requestData.fileType,
                            requestData.degree,
                            requestData.semester,
                            requestData.year
                    ).toDAO(user)
            )
            return ResponseOk(200, "Schedule successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/schedule")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun index(@RequestHeader("Authorization") auth: String)
    : List<ScheduleBO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllSchedules().map {
                it.toBO()
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/schedule/{id}")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun getSchedule(@RequestHeader("Authorization") auth: String,
                    @PathVariable id: Long)
    : ScheduleEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val schedule = service.getSchedule(id)
            if (schedule == null) {
                throw DataException(ExceptionDataModel.SCHEDULE_NOT_EXIST)
            } else {
                if (schedule.userEntity == user) {
                    return schedule.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/schedule/delete/{id}")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun deleteSchedule(@RequestHeader("Authorization") auth: String,
                       @PathVariable id: Long)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteSchedule(id)
            return ResponseOk(200,"Schedule successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/schedule/delete")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun deleteAllSchedules(@RequestHeader("Authorization") auth: String)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteAllSchedules()
            return ResponseOk(200,"Schedule successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }


    @PostMapping("/schedule/update")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun updateSchedule(@RequestHeader("Authorization") auth: String,
                       @RequestBody requestData: ScheduleBO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val list = requestData.subjects.joinToString(separator = ";") { it.toString() }
            service.updateSchedule(
                    ScheduleEntityDTO(
                            list,
                            requestData.scheduleType,
                            requestData.fileType,
                            requestData.degree,
                            requestData.semester,
                            requestData.year,
                            requestData.id
                    ).toDAO(user)
            )
            return ResponseOk(200, "Schedule successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    private fun createDirectory() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root)
            } catch (e: IOException) {
                throw RuntimeException("Could not initialize folder for upload!")
            }
        }
    }

    private fun loadFile(fileName: String): Resource {
        return try {
            val file = root.resolve(fileName)
            val resource: Resource = UrlResource(file.toUri())
            if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw RuntimeException("Could not read the file!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: " + e.message)
        }
    }

}
