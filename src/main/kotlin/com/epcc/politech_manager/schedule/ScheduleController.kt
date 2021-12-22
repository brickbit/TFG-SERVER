package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectService
import com.epcc.politech_manager.utils.CreateScheduleFileBO
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@RestController
class ScheduleController(val service: SubjectService) {

    private val root: Path = Paths.get("scheduleFile")

    @GetMapping("/schedule/build")
    fun buildSchedule(@RequestBody requestData: CreateScheduleFileBO) {
        //service.getSchedule(requestData.id)
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = createComputerScienceDegree(),fileType = fileType, scheduleType = scheduleType)
        buildSchedule.createFile()

    }

    @GetMapping("/schedule/download")
    fun downloadFileFromLocal(@RequestBody requestData: CreateScheduleFileBO): ResponseEntity<*>? {
        //service.getSchedule(requestData.id)
        createDirectory()
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = createComputerScienceDegree(),fileType = fileType, scheduleType = scheduleType)
        val fileName = buildSchedule.createFile()

        val resource = loadFile(fileName)

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename?.toString() + "\"")
                .body<Any?>(resource)
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
