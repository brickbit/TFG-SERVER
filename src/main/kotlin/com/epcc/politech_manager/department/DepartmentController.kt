package com.epcc.politech_manager.department

import org.springframework.web.bind.annotation.*

@RestController
class DepartmentController(val service: DepartmentService) {
    @GetMapping("/department")
    fun index(): List<Departments> = service.findDepartments()

    @PostMapping("/department")
    fun post(@RequestBody department: Departments) {
        service.post(department)
    }

    @GetMapping("/department/{id}")
    fun getDepartment(@PathVariable id: String): Departments {
       return service.getDepartment(id)
    }

    @PostMapping("/department/delete/{id}")
    fun deleteDepartment(@PathVariable id: String) {
        service.deleteDepartment(id)
    }

    @PostMapping("/department/update/{id}")
    fun updateDepartment(@RequestBody name: String, @PathVariable id: String) {
        service.updateDepartment(name, id)
    }
}