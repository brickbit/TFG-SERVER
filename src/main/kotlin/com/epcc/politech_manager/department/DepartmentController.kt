package com.epcc.politech_manager.department

import org.springframework.web.bind.annotation.*

@RestController
class DepartmentController(val service: DepartmentService) {
    @GetMapping("/department")
    fun index(): List<DepartmentEntity> = service.findDepartments()

    @PostMapping("/department")
    fun post(@RequestBody department: DepartmentEntity) {
        service.post(department)
    }

    @GetMapping("/department/{id}")
    fun getDepartment(@PathVariable id: Long): DepartmentEntity? {
       return service.getDepartment(id)
    }

    @PostMapping("/department/delete/{id}")
    fun deleteDepartment(@PathVariable id: Long) {
        service.deleteDepartment(id)
    }

    @PostMapping("/department/update")
    fun updateDepartment(@RequestBody department: DepartmentEntity) {
        service.updateDepartment(department)
    }
}