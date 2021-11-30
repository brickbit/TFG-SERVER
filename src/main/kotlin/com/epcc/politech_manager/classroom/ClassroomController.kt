package com.epcc.politech_manager.classroom

import org.springframework.web.bind.annotation.*

@RestController
class ClassroomController(val service: ClassroomService) {
    @GetMapping("/classroom")
    fun index(): List<Classrooms> = service.getAllClassrooms()

    @PostMapping("/classroom")
    fun post(@RequestBody classroom: Classrooms) {
        service.post(classroom)
    }

    @GetMapping("/classroom/{id}")
    fun getClassroom(@PathVariable id: String): Classrooms {
        return service.getClassroom(id)
    }

    @PostMapping("/classroom/delete/{id}")
    fun deleteClassroom(@PathVariable id: String) {
        service.deleteClassroom(id)
    }

    @PostMapping("/classroom/update/{id}")
    fun updateClassroom(@RequestBody classroom: Classrooms, @PathVariable id: String) {
        service.updateClassroom(classroom, id)
    }
}