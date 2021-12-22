package com.epcc.politech_manager.degree

import org.springframework.web.bind.annotation.*

@RestController
class DegreeController(val service: DegreeService) {

    @GetMapping("/degree")
    fun index(): List<DegreeEntity> = service.getAllDegrees()

    @PostMapping("/degree")
    fun post(@RequestBody degree: DegreeEntity) {
        service.post(degree)
    }

    @GetMapping("/degree/{id}")
    fun getDegree(@PathVariable id: Long): DegreeEntity? {
        return service.getDegree(id)
    }

    @PostMapping("/degree/delete/{id}")
    fun deleteDegree(@PathVariable id: Long) {
        service.deleteDegree(id)
    }

    @PostMapping("/degree/update")
    fun updateDegree(@RequestBody degree: DegreeEntity) {
        service.updateDegree(degree)
    }
}