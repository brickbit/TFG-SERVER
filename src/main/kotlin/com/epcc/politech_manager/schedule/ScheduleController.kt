package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectService
import com.epcc.politech_manager.utils.CreateScheduleFileBO
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class ScheduleController(val service: SubjectService) {

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
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = createComputerScienceDegree(),fileType = fileType, scheduleType = scheduleType)
        val fileName = buildSchedule.createFile()

        val resource: Resource = ClassPathResource(fileName)

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename?.toString() + "\"")
                .body<Any?>(resource)
    }

}
