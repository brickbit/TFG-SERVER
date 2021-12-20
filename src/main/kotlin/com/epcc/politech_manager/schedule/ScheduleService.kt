package com.epcc.politech_manager.schedule

import org.springframework.stereotype.Service

@Service
class ScheduleService(val db: ScheduleRepository) {

    fun getAllSchedules(): List<ScheduleDegree> = db.findAll().toList()

    fun post(schedule: ScheduleDegree) {
        db.save(schedule)
    }

    fun getSchedule(id: Long): ScheduleDegree? {
        return db.findById(id).orElse(null)
    }

    fun deleteSchedule(id: Long) {
        db.deleteById(id)
    }

    fun updateSchedule(subject: ScheduleDegree) {
        db.save(subject)
    }
}