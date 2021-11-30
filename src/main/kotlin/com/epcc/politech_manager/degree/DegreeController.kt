package com.epcc.politech_manager.degree


import org.springframework.web.bind.annotation.*

@RestController
class DegreeController(val service: DegreeService) {
    @GetMapping("/degree")
    fun index(): List<Degrees> = service.getAllDegrees()

    @PostMapping("/degree")
    fun post(@RequestBody degree: Degrees) {
        service.post(degree)
    }

    @GetMapping("/degree/{id}")
    fun getDegree(@PathVariable id: String): Degrees {
        return service.getDegree(id)
    }

    @PostMapping("/degree/delete/{id}")
    fun deleteDegree(@PathVariable id: String) {
        service.deleteDegree(id)
    }

    @PostMapping("/degree/update/{id}")
    fun updateDegree(@RequestBody degree: Degrees, @PathVariable id: String) {
        service.updateDegree(degree, id)
    }
}