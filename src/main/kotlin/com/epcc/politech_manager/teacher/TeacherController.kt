package com.epcc.politech_manager.teacher

import org.springframework.web.bind.annotation.*

@RestController
class TeacherController(val service: TeacherService) {

    @GetMapping("/teacher")
    fun index(): List<TeachersBO> = service.getAllTeachers()

    @PostMapping("/teacher")
    fun post(@RequestBody teacher: Teachers) {
        service.post(teacher)
    }

    @GetMapping("/teacher/{id}")
    fun getTeacher(@PathVariable id: String): TeachersBO {
        return service.getTeacher(id)
    }

    @PostMapping("/teacher/delete/{id}")
    fun deleteTeacher(@PathVariable id: String) {
        service.deleteTeacher(id)
    }

    @PostMapping("/teacher/update/{id}")
    fun updateTeacher(@RequestBody teacher: Teachers, @PathVariable id: String) {
        service.updateTeacher(teacher, id)
    }
}