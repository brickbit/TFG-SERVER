package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.degree.Degrees

data class ScheduleDegree(val degrees: Degrees, val year: String, val semester: SemesterDegree)
data class SemesterDegree(val list: List<List<List<SubjectDegree?>>>)
data class SubjectDegree(val id: String,val name: String, val acronym: String, val group: String, val seminary: Boolean,val laboratory: Boolean, val english: Boolean, val time: Int, val classrooms: List<ClassroomDegree>, val department: DepartmentDegree, val color: Int)
data class ClassroomDegree(val id: String, val name: String, val pavilion: Pavilion)
data class DepartmentDegree(val id: String, val name: String)
enum class Pavilion {
    TELECOMMUNICATION, COMPUTING, ARCHITECTURE, CIVIL_WORK, CENTRAL
}

fun createBuildingSchedule(): ScheduleDegree {
    val mathematicsClassroom = ClassroomDegree("1","C-1B",Pavilion.CIVIL_WORK)
    val buildingClassroom = ClassroomDegree("2","A-1",Pavilion.ARCHITECTURE)
    val designClassroom = ClassroomDegree("3","A-6",Pavilion.ARCHITECTURE)

    val mathematicsDepartment = DepartmentDegree("1","Matemáticas")
    val buildingDepartment = DepartmentDegree("2","Construcción")
    val graphicalExpressionDepartment = DepartmentDegree("3","Expresión gráfica")
    val economicsDepartment = DepartmentDegree("4","Economía")


    val calculusSeminary = SubjectDegree("111","CÁLCULO","SCAL","A",true,false, false,60, listOf(mathematicsClassroom),mathematicsDepartment,2)
    val calculus = SubjectDegree("112","CÁLCULO","CAL","A",false,false,false,180, listOf(buildingClassroom),mathematicsDepartment,2)
    val materialFundamentals = SubjectDegree("113","FUND MATERIALES","FMAT","A",false,false,false,180, listOf(buildingClassroom),buildingDepartment,1)
    val materialFundamentalsLaboratory = SubjectDegree("114","FUND MATERIALES","LFMAT","A",false,true,false,240, listOf(buildingClassroom),buildingDepartment,1)
    val graphicalExpressionFundamentals = SubjectDegree("115","FUND EXP GRÁFICA","FEG","A",false,false,false,120, listOf(designClassroom),graphicalExpressionDepartment,0)
    val structureFundamentals = SubjectDegree("116","FUND ESTRUCTURAS","FE","A",false,false,false,180, listOf(buildingClassroom),buildingDepartment,3)
    val economics = SubjectDegree("117","ECONOMÍA Y EMPRESA","EE","A",false,false,false,180, listOf(buildingClassroom),economicsDepartment,4)
    val graphicalExpressionFundamentalsSeminary = SubjectDegree("119","FUND EXP GRÁFICA","SFEG","A",true,false,false,120, listOf(designClassroom),graphicalExpressionDepartment,0)
    val economicsSeminary = SubjectDegree("1110","ECONOMÍA Y EMPRESA","SEE","A",false,false,false,180, listOf(buildingClassroom),economicsDepartment,4)
    val structureFundamentalsLaboratory = SubjectDegree("1111","FUND ESTRUCTURAS","LFE","A",false,true,false,180, listOf(buildingClassroom),buildingDepartment,3)
    val buildingReservation = SubjectDegree("1112","RESERVADO VISITAS A OBRA","RVO","A",false,false, false,360,listOf(),buildingDepartment,5)

    val degree = Degrees("d0", "Grado en Edificación", 8)
    val schedule = ScheduleDegree(degree, "2021-2022", SemesterDegree(
            list = listOf(
                    listOf(
                            listOf(calculusSeminary),
                            listOf(calculusSeminary),
                            listOf(calculus),
                            listOf(calculus),
                            listOf(materialFundamentals),
                            listOf(materialFundamentals),
                            listOf(materialFundamentals),
                            listOf(materialFundamentals),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory)),
                    listOf(
                            listOf(calculus),
                            listOf(calculus),
                            listOf(graphicalExpressionFundamentals),
                            listOf(graphicalExpressionFundamentals),
                            listOf(structureFundamentals),
                            listOf(structureFundamentals),
                            listOf(materialFundamentals),
                            listOf(materialFundamentals),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory),
                            listOf(materialFundamentalsLaboratory)),
                    listOf(
                            listOf(economics),
                            listOf(economics),
                            listOf(economics),
                            listOf(economics),
                            listOf(structureFundamentals),
                            listOf(structureFundamentals),
                            listOf(graphicalExpressionFundamentals),
                            listOf(graphicalExpressionFundamentals),
                            listOf(graphicalExpressionFundamentalsSeminary),
                            listOf(graphicalExpressionFundamentalsSeminary),
                            listOf(graphicalExpressionFundamentalsSeminary),
                            listOf(graphicalExpressionFundamentalsSeminary)),
                    listOf(
                            listOf(economics),
                            listOf(economics),
                            listOf(economicsSeminary),
                            listOf(economicsSeminary),
                            listOf(calculus),
                            listOf(calculus),
                            listOf(structureFundamentals),
                            listOf(structureFundamentals),
                            listOf(structureFundamentalsLaboratory),
                            listOf(structureFundamentalsLaboratory),
                            listOf(structureFundamentalsLaboratory),
                            listOf(structureFundamentalsLaboratory)),
                    listOf(
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation),
                            listOf(buildingReservation))
            )
    ))

    return schedule
}

fun createComputerScienceDegree(): ScheduleDegree {
    val degree = Degrees("d1", "Grado en Ingeniería informática en ingeniería de computadores", 8)

    val physicsLaboratoryClassroom = ClassroomDegree("i0l","Laboratorio de física",Pavilion.COMPUTING)
    val ipLaboratoryClassroom = ClassroomDegree("i1l1","Sala 1",Pavilion.COMPUTING)
    val mathematicsClassroom = ClassroomDegree("1","C-1B",Pavilion.CIVIL_WORK)

    val physicsDepartment = DepartmentDegree("0","Física aplicada")
    val mathematicsDepartment = DepartmentDegree("1","Matemáticas")
    val softwareDepartment = DepartmentDegree("2","Ingeniería de sistemas informáticos y telemáticos")

    val physics = SubjectDegree("sic0l","Física","FIS","A",false,false,false,1200, listOf(physicsLaboratoryClassroom),physicsDepartment,4)
    val physicsLaboratory = SubjectDegree("sic0l","Laboratorio Física","LFIS","A",false,true,false,1200, listOf(physicsLaboratoryClassroom),physicsDepartment,4)
    val ip = SubjectDegree("sic1l","Introducción a la programación","IP","A",false,false,false,1080, listOf(ipLaboratoryClassroom),softwareDepartment,0)
    val ipLaboratory = SubjectDegree("sic1l","Laboratorio Introducción a la programación","LIP","A",false,true,false,1080, listOf(ipLaboratoryClassroom),softwareDepartment,0)
    val algebraSeminary = SubjectDegree("sic2s","Seminario Algebra","SAL","A",true,false,false,60, listOf(mathematicsClassroom),mathematicsDepartment,2)
    val algebraEnglish = SubjectDegree("sic2s","Algebra","AL","",false,false,true,60, listOf(mathematicsClassroom),mathematicsDepartment,2)
    val algebra = SubjectDegree("sic2s","Algebra","AL","A",false,false,false,60, listOf(mathematicsClassroom),mathematicsDepartment,2)
    val calculusSeminary = SubjectDegree("sic3s","Seminario Cálculo","SCAL","A",true,false,false,60, listOf(mathematicsClassroom),mathematicsDepartment,3)
    val calculus = SubjectDegree("sic3s","Cálculo","CAL","A",false,false,false,60, listOf(mathematicsClassroom),mathematicsDepartment,3)
    val tcLaboratory = SubjectDegree("sic4l","Laboratorio Tecnología de computadores","LTC","A",false,true,false,1080, listOf(ipLaboratoryClassroom),softwareDepartment,1)
    val tc = SubjectDegree("sic4l","Tecnología de computadores","LTC","A",false,false,false,1080, listOf(ipLaboratoryClassroom),softwareDepartment,1)

    val schedule = ScheduleDegree(degree, "2021-2022", SemesterDegree(
            list = listOf(
                    listOf(
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null, null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,ipLaboratory,null,null,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,ipLaboratory,null,null,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,ipLaboratory,null,null,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,tcLaboratory),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,ipLaboratory,null,null,tcLaboratory,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,ipLaboratory,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,ipLaboratory,null,null,null,null,null),
                            listOf(),
                            listOf(),
                            listOf(),
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                    ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics,null,null,ip,null,null,null,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra,null,null,calculus,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,calculus,null,null,null,null,null,null,null,null,tc,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                    ),
                    listOf(
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,algebraEnglish,null,null,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,algebraEnglish,null,null,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(algebraSeminary,null,null,calculusSeminary,null,null,physicsLaboratory,null,null,null,null,null,tcLaboratory,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            )

            )
    ))
    return schedule

}