package com.epcc.politech_manager.schedule

import org.springframework.stereotype.Service

@Service
class ScheduleService(val db: ScheduleRepository) {

    fun getAllSchedules(): List<SchedulesBO> = db.getAllSchedules()

    fun post(schedule: Schedules) {
        db.save(schedule)
    }

    fun getSchedule(id: String): SchedulesBO {
        return db.getSchedule(id)
    }

    fun deleteSchedule(id: String) {
        db.deleteSchedule(id)
    }

    fun updateSchedule(subject: SchedulesBO, id: String) {
        db.updateSchedules(
                subject.start_hour,
                subject.end_hour,
                subject.id_subject,
                id)
    }
}