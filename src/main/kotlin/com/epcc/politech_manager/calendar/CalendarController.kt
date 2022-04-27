package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
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
class CalendarController(val service: CalendarService, val userService: UserService) {

    private val root: Path = Paths.get("calendarFile")

    @GetMapping("/calendar/download")
    fun downloadFileFromLocal(@RequestHeader("Authorization") auth: String,
                              @RequestBody requestData: CalendarEntityDTO): ResponseEntity<*>? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            createDirectory()
            service.initData(
                    exams = requestData.exams,
                    degree = requestData.degree,
                    year = requestData.year,
                    startDate = requestData.startDate,
                    endDate = requestData.endDate,
                    call = requestData.call
            )
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

    @GetMapping("/calendar")
    fun index(@RequestHeader("Authorization") auth: String): List<CalendarEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val calendars = service.getAllCalendars().filter { it.userEntity == user }
            return calendars.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/calendar")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody requestData: CalendarEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val calendarDAO = requestData.toDAO(user)
            service.post(calendarDAO)
            return ResponseOk(200, "Calendar successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/calendar/{id}")
    fun getCalendar(@RequestHeader("Authorization") auth: String,
                  @PathVariable id: Long)
            : CalendarEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val calendar = service.getCalendar(id)
            if (calendar == null) {
                throw DataException(ExceptionDataModel.CLASSROOM_NOT_EXIST)
            } else {
                if (calendar.userEntity == user) {
                    return calendar.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/calendar/delete/{id}")
    fun deleteCalendar(@RequestHeader("Authorization") auth: String,
                     @PathVariable id: Long)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteCalendar(id)
            return ResponseOk(200,"Calendar successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/calendar/update")
    fun updateCalendar(@RequestHeader("Authorization") auth: String,
                     @RequestBody calendar: CalendarEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateCalendar(calendar.toDAO(user))
            return ResponseOk(200,"Calendar successfully updated")
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