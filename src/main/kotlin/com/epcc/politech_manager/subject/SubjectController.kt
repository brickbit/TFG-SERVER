package com.epcc.politech_manager.subject

import org.springframework.web.bind.annotation.*

@RestController
class SubjectController(val service: SubjectService) {

    @GetMapping("/subject")
    fun index(): List<SubjectsBO> = service.getAllSubject()

    @PostMapping("/subject")
    fun post(@RequestBody subject: Subjects) {
        service.post(subject)
    }

    @GetMapping("/subject/{id}")
    fun getSubject(@PathVariable id: String): SubjectsBO {
        return service.getSubject(id)
    }

    @PostMapping("/subject/delete/{id}")
    fun deleteSubject(@PathVariable id: String) {
        service.deleteSubject(id)
    }

    @PostMapping("/subject/update/{id}")
    fun updateSubject(@RequestBody subject: SubjectsBO, @PathVariable id: String) {
        service.updateSubject(subject, id)
    }
}