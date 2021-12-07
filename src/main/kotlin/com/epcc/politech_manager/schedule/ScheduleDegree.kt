package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.degree.Degrees

data class ScheduleDegree(val degrees: Degrees, val semester: SemesterDegree)
data class SemesterDegree(val list: List<List<SubjectDegree>>)
data class SubjectDegree(val id: String,val name: String, val acronym: String, val seminary: Boolean,val laboratory: Boolean, val time: Int, val classrooms: List<ClassroomDegree>, val department: DepartmentDegree)
data class ClassroomDegree(val id: String, val name: String, val pavilion: Pavilion)
data class DepartmentDegree(val id: String, val name: String)
enum class Pavilion {
    TELECOMMUNICATION, COMPUTING, ARCHITECTURE, CIVIL_WORK, CENTRAL
}

fun createSchedule(): ScheduleDegree {
    val mathematicsClassroom = ClassroomDegree("1","C-1B",Pavilion.CIVIL_WORK)
    val buildingClassroom = ClassroomDegree("2","A-1",Pavilion.ARCHITECTURE)
    val designClassroom = ClassroomDegree("3","A-6",Pavilion.ARCHITECTURE)

    val mathematicsDepartment = DepartmentDegree("1","Matemáticas")
    val buildingDepartment = DepartmentDegree("2","Construcción")
    val graphicalExpressionDepartment = DepartmentDegree("3","Expresión gráfica")
    val economicsDepartment = DepartmentDegree("4","Economía")


    val calculusSeminary = SubjectDegree("se0s","Seminario Cálculo","SCAL",true,false,60, listOf(mathematicsClassroom),mathematicsDepartment)
    val calculus = SubjectDegree("se0","Cálculo","CAL",false,false,180, listOf(buildingClassroom),mathematicsDepartment)
    val materialFundamentals = SubjectDegree("se1","Fundamento de materiales","FMAT",false,false,180, listOf(buildingClassroom),buildingDepartment)
    val materialFundamentalsLaboratory = SubjectDegree("se1","Laboratorio Fundamento de materiales","LFMAT",false,true,240, listOf(buildingClassroom),buildingDepartment)
    val graphicalExpressionFundamentals = SubjectDegree("se2","Fundamentos de expresión gráfica","FEG",false,false,120, listOf(designClassroom),graphicalExpressionDepartment)
    val structureFundamentals = SubjectDegree("se3","Fundamentos de estructuras","FE",false,false,180, listOf(buildingClassroom),buildingDepartment)
    val economics = SubjectDegree("se4","Economía y empresa","EE",false,false,180, listOf(buildingClassroom),economicsDepartment)
    val graphicalExpressionFundamentalsSeminary = SubjectDegree("se2s","Seminario Fundamentos de expresión gráfica","SFEG",true,false,120, listOf(designClassroom),graphicalExpressionDepartment)
    val economicsSeminary = SubjectDegree("se4","Seminario Economía y empresa","SEE",false,false,180, listOf(buildingClassroom),economicsDepartment)
    val structureFundamentalsLaboratory = SubjectDegree("se3l","Laboratorio Fundamentos de estructuras","LFE",false,true,180, listOf(buildingClassroom),buildingDepartment)
    val buildingReservation = SubjectDegree("ro","Reservado visitas a obra","RVO",false,false, 360,listOf(),buildingDepartment)

    val degree = Degrees("d0", "Grado en Edificación", 8)
    val schedule = ScheduleDegree(degree, SemesterDegree(
            list = listOf(
                    listOf(calculusSeminary,calculusSeminary,calculus,calculus,materialFundamentals,materialFundamentals,materialFundamentals,materialFundamentals,materialFundamentalsLaboratory,materialFundamentalsLaboratory,materialFundamentalsLaboratory,materialFundamentalsLaboratory),
                    listOf(calculus,calculus,graphicalExpressionFundamentals,graphicalExpressionFundamentals,structureFundamentals,structureFundamentals,materialFundamentals,materialFundamentals,materialFundamentalsLaboratory,materialFundamentalsLaboratory,materialFundamentalsLaboratory,materialFundamentalsLaboratory),
                    listOf(economics,economics,economics,economics,structureFundamentals,structureFundamentals,graphicalExpressionFundamentals,graphicalExpressionFundamentals,graphicalExpressionFundamentalsSeminary,graphicalExpressionFundamentalsSeminary,graphicalExpressionFundamentalsSeminary,graphicalExpressionFundamentalsSeminary),
                    listOf(economics,economics,economicsSeminary,economicsSeminary,calculus,calculus,structureFundamentals,structureFundamentals,structureFundamentalsLaboratory,structureFundamentalsLaboratory,structureFundamentalsLaboratory,structureFundamentalsLaboratory),
                    listOf(buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation,buildingReservation)
            )
    ))

    return schedule
}