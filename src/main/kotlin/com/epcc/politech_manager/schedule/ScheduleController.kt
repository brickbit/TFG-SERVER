package com.epcc.politech_manager.schedule

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


@RestController
class ScheduleController(val service: ScheduleService) {

    val excelService = ExcelService()

    @GetMapping("/schedule")
    fun index(): List<SchedulesBO> {
        return service.getAllSchedules()
    }

    @PostMapping("/schedule")
    fun post(@RequestBody schedules: Schedules) {
        service.post(schedules)
    }

    @GetMapping("/schedule/{id}")
    fun getSchedule(@PathVariable id: String): SchedulesBO {
        return service.getSchedule(id)
    }

    @PostMapping("/schedule/delete/{id}")
    fun deleteSchedule(@PathVariable id: String) {
        service.deleteSchedule(id)
    }

    @PostMapping("/schedule/update/{id}")
    fun updateSchedule(@RequestBody schedule: SchedulesBO, @PathVariable id: String) {
        service.updateSchedule(schedule, id)
    }

    @GetMapping("/schedule/build")
    fun buildSchedule() {
        excelService.createFile()
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