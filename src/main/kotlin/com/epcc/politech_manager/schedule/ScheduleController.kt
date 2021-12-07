package com.epcc.politech_manager.schedule

import org.springframework.web.bind.annotation.*

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

    @GetMapping("/schedule/download")
    fun downloadSchedule() {
        excelService.createFile()
    }

}