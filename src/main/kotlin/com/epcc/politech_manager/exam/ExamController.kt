package com.epcc.politech_manager.exam

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
class ExamController(val service: ExamService, val userService: UserService) {

    @GetMapping("/exam")
    fun index(@RequestHeader("Authorization") auth: String): List<ExamEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllExams().filter { it.userEntity == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/exam")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody exam: ExamEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(exam.toDAO(user))
            return ResponseOk(200, "Exam successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/exam/{id}")
    fun getExam(@RequestHeader("Authorization") auth: String,
                @PathVariable id: Long)
            : ExamEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val exam = service.getExam(id)
            if (exam == null) {
                throw DataException(ExceptionDataModel.EXAM_NOT_EXIST)
            } else {
                if (exam.userEntity == user) {
                    return exam.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/exam/delete/{id}")
    fun deleteExam(@RequestHeader("Authorization") auth: String,
                   @PathVariable id: Long)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteExam(id)
            return ResponseOk(200, "Exam successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/exam/update")
    fun updateExam(@RequestHeader("Authorization") auth: String,
                   @RequestBody exam: ExamEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateExam(exam.toDAO(user))
            return ResponseOk(200, "Exam successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}
