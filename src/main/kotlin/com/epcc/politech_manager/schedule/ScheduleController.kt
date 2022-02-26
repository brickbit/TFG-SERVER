package com.epcc.politech_manager.schedule

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
class ScheduleController(val service: CreateScheduleFileService) {

    private val root: Path = Paths.get("scheduleFile")

    @GetMapping("/schedule/download")
    fun downloadFileFromLocal(@RequestBody requestData: CreateScheduleFileBO): ResponseEntity<*>? {
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
    }

    @PostMapping("/schedule")
    fun post(@RequestBody requestData: CreateScheduleFileBO) {
        val list = flatMatrix(requestData.subjects)
        service.post(ScheduleEntity(Gson().toJson(list),requestData.scheduleType,requestData.fileType,requestData.degree,requestData.year))
    }

    @GetMapping("/schedule")
    fun index(): List<CreateScheduleFileBO> = service.getAllSchedules().map {
        it.toScheduleFileBO()
    }

    @GetMapping("/schedule/{id}")
    fun getSchedule(@PathVariable id: Long): ScheduleEntity? {
        return service.getSchedule(id)
    }

    @PostMapping("/schedule/delete/{id}")
    fun deleteSchedule(@PathVariable id: Long) {
        service.deleteSchedule(id)
    }

    @PostMapping("/schedule/update")
    fun updateSchedule(@RequestBody schedule: ScheduleEntity) {
        service.updateSchedule(schedule)
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
