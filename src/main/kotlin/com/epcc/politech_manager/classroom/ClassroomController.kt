package com.epcc.politech_manager.classroom

import org.springframework.web.bind.annotation.*

@RestController
class ClassroomController(val service: ClassroomService) {
    @GetMapping("/classroom")
    fun index(): List<ClassroomEntity> = service.getAllClassrooms()

    @PostMapping("/classroom")
    fun post(@RequestBody classroom: ClassroomEntity) {
        service.post(classroom)
    }

    @GetMapping("/classroom/{id}")
    fun getClassroom(@PathVariable id: Long): ClassroomEntity? {
        return service.getClassroom(id)
    }

    @PostMapping("/classroom/delete/{id}")
    fun deleteClassroom(@PathVariable id: Long) {
        service.deleteClassroom(id)
    }

    @PostMapping("/classroom/update")
    fun updateClassroom(@RequestBody classroom: ClassroomEntity) {
        service.updateClassroom(classroom)
    }
}