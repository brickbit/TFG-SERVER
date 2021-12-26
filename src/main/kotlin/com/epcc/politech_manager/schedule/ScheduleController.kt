package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.classroom.toBO
import com.epcc.politech_manager.degree.toBO
import com.epcc.politech_manager.department.toBO
import com.epcc.politech_manager.subject.Subject
import com.epcc.politech_manager.subject.SubjectEntity
import com.epcc.politech_manager.subject.SubjectService
import com.epcc.politech_manager.subject.toEntity
import com.epcc.politech_manager.utils.*
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@RestController
class ScheduleController(val service: SubjectService) {

    private val root: Path = Paths.get("scheduleFile")

    @GetMapping("/schedule/download")
    fun downloadFileFromLocal(@RequestBody requestData: CreateScheduleFileBO): ResponseEntity<*>? {
        createDirectory()
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = requestData.subjects,fileType = fileType, scheduleType = scheduleType, degree = requestData.degree, year = requestData.year)
        val fileName = buildSchedule.createFile()

        val resource = loadFile(fileName)

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename?.toString() + "\"")
                .body<Any?>(resource)
    }

    private fun createDirectory() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root)
            } catch (e: IOException) {
                throw RuntimeException("Could not initialize folder for upload!")
            }
        }
    }

    private fun loadFile(fileName: String): Resource {
        return try {
            val file = root.resolve(fileName)
            val resource: Resource = UrlResource(file.toUri())
            if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw RuntimeException("Could not read the file!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: " + e.message)
        }
    }

    private fun parse3DMatrixSubjectToListEntity(matrix: List<List<List<Subject?>>>): List<SubjectEntity> {
        val listSubject = mutableListOf<SubjectEntity>()
        matrix.mapIndexed { i, hour ->
            hour.mapIndexed { j, turn ->
                turn.mapIndexed { k, subject ->
                    subject?.let {
                        listSubject.add(it.toEntity("$i","$j","$k"))
                    }
                }
            }
        }
        val groupedList = listSubject.groupBy {
            it.id
        }
        val finalList = mutableListOf<SubjectEntity>()
        groupedList.values.map { groupedSubjects ->
            var days = ""
            var hours = ""
            var turns = ""
            var subjectToCopy: SubjectEntity? = null
            groupedSubjects.map {
                days = days + it.days + ","
                hours = hours + it.hours + ","
                turns = turns + it.turns + ","
                subjectToCopy = it
            }
            subjectToCopy?.let {
                finalList.add(SubjectEntity(
                        it.name,
                        it.acronym,
                        it.classGroup,
                        it.seminary,
                        it.laboratory,
                        it.english,
                        it.time,
                        it.semester,
                        days,
                        hours,
                        turns,
                        it.classroom,
                        it.department,
                        it.degree,
                        it.color,
                        it.id
                )
                )
            }

        }
        return finalList
    }

    private fun parseListSubjectEntityTo3DMatrix(subjects: List<SubjectEntity>, scheduleType: ScheduleType): List<List<List<Subject?>>>{
        val list: MutableList<SubjectEntity> = mutableListOf()
        val matrix: MutableList<MutableList<MutableList<Subject?>>> = when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                MutableList(5){ MutableList(24) { MutableList(1){ null } } }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                MutableList(5){ MutableList(24) { MutableList(15){ null } } }
            }
        }
        subjects.map { subject ->
            val daysArray = subject.days.split(",")
            (daysArray as ArrayList).removeAt(daysArray.size - 1)
            val hoursArray = subject.hours.split(",")
            (hoursArray as ArrayList).removeAt(hoursArray.size - 1)
            val turnsArray = subject.turns.split(",")
            (turnsArray as ArrayList).removeAt(turnsArray.size - 1)
            daysArray.mapIndexed { i, _ ->
                val subjectAux = subject.copy(days = daysArray[i],hours = hoursArray[i],turns = turnsArray[i])
                list.add(subjectAux)
            }
        }
        list.map {
            val day = it.days.toInt()
            val hour = it.hours.toInt()
            val turn = it.turns.toInt()
            matrix[day][hour][turn] = Subject(
                    it.name,
                    it.acronym,
                    it.classGroup,
                    it.seminary,
                    it.laboratory,
                    it.english,
                    it.time,
                    it.semester,
                    it.classroom.toBO(),
                    it.department.toBO(),
                    it.degree.toBO(),
                    it.color,
                    it.id)
        }
        return matrix
    }

    private fun printMatrix(matrix: List<List<List<Subject?>>>) {
        println(matrix)
    }
}
