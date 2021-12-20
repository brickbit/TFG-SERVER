package com.epcc.politech_manager.subject

import org.springframework.web.bind.annotation.*

@RestController
class SubjectController(val service: SubjectService) {

    @GetMapping("/subject")
    fun index(): List<Subject> = service.getAllSubject()

    @PostMapping("/subject")
    fun post(@RequestBody subject: Subject) {
        service.post(subject)
    }

    @GetMapping("/subject/{id}")
    fun getSubject(@PathVariable id: Long): Subject? {
        return service.getSubject(id)
    }

    @PostMapping("/subject/delete/{id}")
    fun deleteSubject(@PathVariable id: Long) {
        service.deleteSubject(id)
    }

    @PostMapping("/subject/update")
    fun updateSubject(@RequestBody subject: Subject) {
        service.updateSubject(subject)
    }
}