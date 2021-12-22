package com.epcc.politech_manager.subject

import org.springframework.web.bind.annotation.*

@RestController
class SubjectController(val service: SubjectService) {

    @GetMapping("/subject")
    fun index(): List<SubjectEntity> = service.getAllSubject()

    @PostMapping("/subject")
    fun post(@RequestBody subject: SubjectEntity?) {
        service.post(subject)
    }

    @GetMapping("/subject/{id}")
    fun getSubject(@PathVariable id: Long): SubjectEntity? {
        return service.getSubject(id)
    }

    @PostMapping("/subject/delete/{id}")
    fun deleteSubject(@PathVariable id: Long) {
        service.deleteSubject(id)
    }

    @PostMapping("/subject/update")
    fun updateSubject(@RequestBody subject: SubjectEntity) {
        service.updateSubject(subject)
    }
}