package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.utils.CreateScheduleFileBO
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.net.MalformedURLException
import java.nio.file.Path
import java.nio.file.Paths

/*
@RestController
class ScheduleController(val service: ScheduleService) {

    @GetMapping("/schedule")
    fun index(): List<ScheduleDegree> {
        return service.getAllSchedules()
    }

    @PostMapping("/schedule")
    fun post(@RequestBody schedule: ScheduleDegree) {
        service.post(schedule)
    }

    @GetMapping("/schedule/{id}")
    fun getSchedule(@PathVariable id: Long): ScheduleDegree? {
        return service.getSchedule(id)
    }

    @PostMapping("/schedule/delete/{id}")
    fun deleteSchedule(@PathVariable id: Long) {
        service.deleteSchedule(id)
    }

    @PostMapping("/schedule/update")
    fun updateSchedule(@RequestBody schedule: ScheduleDegree) {
        service.updateSchedule(schedule)
    }

    @GetMapping("/schedule/build")
    fun buildSchedule(@RequestBody requestData: CreateScheduleFileBO) {
        //service.getSchedule(requestData.id)
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = createComputerScienceDegree(),fileType = fileType, scheduleType = scheduleType)
        buildSchedule.createFile()
    }

    @GetMapping("/schedule/download")
    fun downloadFileFromLocal(): ResponseEntity<*>? {
        val currDir = File(".")

        val path: Path = Paths.get(currDir.absolutePath.substring(0, currDir.absolutePath.length - 1) + "schedule.xlsx")
        var resource: Resource? = null
        try {
            resource = UrlResource(path.toUri())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource?.filename.toString() + "\"")
                .body<Any?>(resource)
    }

}

 */