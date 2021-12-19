package com.epcc.politech_manager.classroom

import org.springframework.web.bind.annotation.*

@RestController
class ClassroomController(val service: ClassroomService) {
    @GetMapping("/classroom")
    fun index(): List<Classroom> = service.getAllClassrooms()

    @PostMapping("/classroom")
    fun post(@RequestBody classroom: Classroom) {
        service.post(classroom)
    }

    @GetMapping("/classroom/{id}")
    fun getClassroom(@PathVariable id: Long): Classroom? {
        return service.getClassroom(id)
    }

    @PostMapping("/classroom/delete/{id}")
    fun deleteClassroom(@PathVariable id: Long) {
        service.deleteClassroom(id)
    }

    @PostMapping("/classroom/update/{id}")
    fun updateClassroom(@RequestBody classroom: Classroom) {
        service.updateClassroom(classroom)
    }
}