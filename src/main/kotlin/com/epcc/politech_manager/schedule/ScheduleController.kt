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

    @GetMapping("/schedule/build")
    fun buildSchedule(@RequestBody requestData: CreateScheduleFileBO) {
        //service.getSchedule(requestData.id)
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = createComputerScienceDegree(),fileType = fileType, scheduleType = scheduleType)
        buildSchedule.createFile()

    }

    @GetMapping("/schedule/download")
    fun downloadFileFromLocal(@RequestBody requestData: CreateScheduleFileBO): ResponseEntity<*>? {
        //service.getSchedule(requestData.id)
        val list = parse3DMatrixSubjectToListEntity(createComputerScienceDegree())
        val matrix = parseListSubjectEntityTo3DMatrix(list,requestData.scheduleType.toScheduleType())
        createDirectory()
        val scheduleType = requestData.scheduleType.toScheduleType()
        val fileType = requestData.fileType.toFileType()
        val buildSchedule = CreateScheduleFileService(scheduleData = requestData.subjects,fileType = fileType, scheduleType = scheduleType)
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
        val matrix: MutableList<MutableList<MutableList<Subject?>>> = when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                MutableList(5){ MutableList(24) { MutableList(1){ null } } }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                MutableList(5){ MutableList(24) { MutableList(15){ null } } }
            }
        }
        subjects.map { subject ->
            println(subject)
            val daysArray = subject.days.split(",")
            (daysArray as ArrayList).removeAt(daysArray.size - 1)
            val days = daysArray.map { it.toInt() }
            val hoursArray = subject.hours.split(",")
            (hoursArray as ArrayList).removeAt(hoursArray.size - 1)
            val hours = hoursArray.map { it.toInt() }
            val turnsArray = subject.days.split(",")
            (turnsArray as ArrayList).removeAt(turnsArray.size - 1)
            val turns = turnsArray.map { it.toInt() }
            daysArray.mapIndexed { i, _ ->
                matrix[days[i]][hours[i]][turns[i]] = Subject(
                        subject.name,
                        subject.acronym,
                        subject.classGroup,
                        subject.seminary,
                        subject.laboratory,
                        subject.english,
                        subject.time,
                        subject.semester,
                        subject.classroom.toBO(),
                        subject.department.toBO(),
                        subject.degree.toBO(),
                        subject.color,
                        subject.id)
            }
        }
        return matrix
    }
}
