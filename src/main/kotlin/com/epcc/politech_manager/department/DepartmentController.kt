package com.epcc.politech_manager.department

import org.springframework.web.bind.annotation.*

@RestController
class DepartmentController(val service: DepartmentService) {
    @GetMapping("/department")
    fun index(): List<Department> = service.findDepartments()

    @PostMapping("/department")
    fun post(@RequestBody department: Department) {
        service.post(department)
    }

    @GetMapping("/department/{id}")
    fun getDepartment(@PathVariable id: Long): Department? {
       return service.getDepartment(id)
    }

    @PostMapping("/department/delete/{id}")
    fun deleteDepartment(@PathVariable id: Long) {
        service.deleteDepartment(id)
    }

    @PostMapping("/department/update")
    fun updateDepartment(@RequestBody department: Department) {
        service.updateDepartment(department)
    }
}