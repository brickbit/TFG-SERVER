package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.classroom.Classroom
import com.epcc.politech_manager.degree.Degrees

data class ScheduleDegree(val degrees: Degrees, val year: String, val semester: SemesterDegree)
data class SemesterDegree(val num: Int, val subjectsInSemester: List<List<List<SubjectDegree?>>>)
data class SubjectDegree(val id: String, val name: String, val acronym: String, val group: String, val seminary: Boolean, val laboratory: Boolean, val english: Boolean, val time: Int, val classrooms: List<Classroom>, val department: DepartmentDegree, val color: Int)
data class DepartmentDegree(val id: String, val name: String, val acronym: String)
enum class Pavilion {
    TELECOMMUNICATION, COMPUTING, ARCHITECTURE, CIVIL_WORK, CENTRAL
}

fun createBuildingSchedule(): ScheduleDegree {
    val mathematicsClassroom = Classroom("C-1B",Pavilion.CIVIL_WORK, "C-1B",1)
    val buildingClassroom = Classroom("A-1",Pavilion.ARCHITECTURE, "A-1",2)
    val designClassroom = Classroom("A-6",Pavilion.ARCHITECTURE, "A-6",3)

    val mathematicsDepartment = DepartmentDegree("1","Matemáticas","MAT")
    val buildingDepartment = DepartmentDegree("2","Construcción","CONS")
    val graphicalExpressionDepartment = DepartmentDegree("3","Expresión gráfica","EX.GR")
    val economicsDepartment = DepartmentDegree("4","Economía","EC")


    val calculusSeminary = SubjectDegree("111","CÁLCULO","SCAL","A",true,false, false,60, listOf(mathematicsClassroom),mathematicsDepartment,2)
    val calculus = SubjectDegree("112","CÁLCULO","CAL","A",false,false,false,180, listOf(buildingClassroom),mathematicsDepartment,2)
    val materialFundamentals = SubjectDegree("113","FUND MATERIALES","FMAT","A",false,false,false,180, listOf(buildingClassroom),buildingDepartment,1)
    val materialFundamentalsLaboratory = SubjectDegree("114","FUND MATERIALES","LFMAT","A",false,true,false,240, listOf(buildingClassroom),buildingDepartment,1)
    val graphicalExpressionFundamentals = SubjectDegree("115","FUND EXP GRÁFICA","FEG","A",false,false,false,120, listOf(designClassroom),graphicalExpressionDepartment,0)
    val structureFundamentals = SubjectDegree("116","FUND ESTRUCTURAS","FE","A",false,false,false,180, listOf(buildingClassroom),buildingDepartment,3)
    val economics = SubjectDegree("117","ECONOMÍA Y EMPRESA","EE","A",false,false,false,180, listOf(buildingClassroom),economicsDepartment,4)
    val graphicalExpressionFundamentalsSeminary = SubjectDegree("119","FUND EXP GRÁFICA","SFEG","A",true,false,false,120, listOf(designClassroom),graphicalExpressionDepartment,0)
    val economicsSeminary = SubjectDegree("1110","E. y E.","SEE","A",true,false,false,180, listOf(buildingClassroom),economicsDepartment,4)
    val structureFundamentalsLaboratory = SubjectDegree("1111","FUND ESTRUCTURAS","LFE","A",false,true,false,180, listOf(buildingClassroom),buildingDepartment,3)
    val buildingReservation = SubjectDegree("1112","RESERVADO \nVISITAS\n A OBRA","RVO","",false,false, false,360,listOf(buildingClassroom),buildingDepartment,5)

    val degree = Degrees("d0", "Grado en Edificación", 8)
    val schedule = ScheduleDegree(degree, "2021-2022", SemesterDegree(num = 0,
            subjectsInSemester = listOf(
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
                            listOf(materialFundamentalsLaboratory),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null)),
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
                            listOf(materialFundamentalsLaboratory),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null)),
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
                            listOf(graphicalExpressionFundamentalsSeminary),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null)),
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
                            listOf(structureFundamentalsLaboratory),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null)),
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
                            listOf(buildingReservation),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null),
                            listOf(null))
            )
    ))

    return schedule
}

fun createComputerScienceDegree(): ScheduleDegree {
    val degree = Degrees("d1", "Grado en Ingeniería informática en ingeniería de computadores", 8)

    val c3 = Classroom("C-3",Pavilion.CENTRAL, "C-3",1)
    val c1b = Classroom("C-1B",Pavilion.CIVIL_WORK, "C-1B",2)
    val c6 = Classroom("C-6",Pavilion.CENTRAL, "C-6",3)
    val c4 = Classroom("C-4",Pavilion.CENTRAL, "C-4",4)
    val o5 = Classroom("O-5",Pavilion.CIVIL_WORK, "O-5",5)
    val c1 = Classroom("C-1",Pavilion.CENTRAL, "C-1",6)
    val c2 = Classroom("C-2",Pavilion.CENTRAL, "C-2",7)
    val lFis = Classroom("Laboratorio de física",Pavilion.COMPUTING, "LFIS",8)
    val sala1 = Classroom("Sala 1",Pavilion.COMPUTING, "SALA1",9)
    val laboratorio1 = Classroom("Laboratorio 1",Pavilion.COMPUTING, "LAB1",10)
    val novell = Classroom("Novell",Pavilion.COMPUTING, "NOV",11)
    val labDigSis = Classroom("Lab-Sistemas Digitales",Pavilion.COMPUTING, "LSD",12)
    val labC3b = Classroom("Lab-C-3B",Pavilion.COMPUTING, "LC3B",13)


    val physicsDepartment = DepartmentDegree("0","Física aplicada","FIS")
    val mathematicsDepartment = DepartmentDegree("1","Matemáticas","MAT")
    val softwareDepartment = DepartmentDegree("2","Ingeniería de sistemas informáticos y telemáticos","ISIT")
    val computerDepartment = DepartmentDegree("2","Tecnología de los computares y de las comunicaciones","TCC")

    val algebraSeminary1 = SubjectDegree("1","Seminario Algebra","SAL1","1",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary2 = SubjectDegree("2","Seminario Algebra","SAL2","2",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary3 = SubjectDegree("3","Seminario Algebra","SAL3","3",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary4 = SubjectDegree("4","Seminario Algebra","SAL4","4",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary5 = SubjectDegree("5","Seminario Algebra","SAL5","5",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary6 = SubjectDegree("6","Seminario Algebra","SAL6","6",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary7 = SubjectDegree("7","Seminario Algebra","SAL7","7",true,false,false,60, listOf(c3),mathematicsDepartment,2)
    val algebraSeminary8 = SubjectDegree("8","Seminario Algebra","SAL8","8",true,false,false,60, listOf(c3),mathematicsDepartment,2)

    val calculusSeminary1 = SubjectDegree("9","Seminario Cálculo","SCAL1","1",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary2 = SubjectDegree("10","Seminario Cálculo","SCAL2","2",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary3 = SubjectDegree("11","Seminario Cálculo","SCAL3","3",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary4 = SubjectDegree("12","Seminario Cálculo","SCAL4","4",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary5 = SubjectDegree("13","Seminario Cálculo","SCAL5","5",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary6 = SubjectDegree("14","Seminario Cálculo","SCAL6","6",true,false,false,60, listOf(c4),mathematicsDepartment,3)
    val calculusSeminary7 = SubjectDegree("15","Seminario Cálculo","SCAL7","7",true,false,false,60, listOf(c4),mathematicsDepartment,3)

    val physicsLaboratory1 = SubjectDegree("16","Laboratorio Física","LFIS1","1|2",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory2 = SubjectDegree("17","Laboratorio Física","LFIS2","3|4",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory3 = SubjectDegree("18","Laboratorio Física","LFIS3","5|6",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory4 = SubjectDegree("19","Laboratorio Física","LFIS4","7|8",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory5 = SubjectDegree("20","Laboratorio Física","LFIS5","9",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory6 = SubjectDegree("21","Laboratorio Física","LFIS6","10",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory7 = SubjectDegree("22","Laboratorio Física","LFIS7","11",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory8 = SubjectDegree("23","Laboratorio Física","LFIS8","12|13",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory9 = SubjectDegree("24","Laboratorio Física","LFIS9","14|15",false,true,false,1200, listOf(lFis),physicsDepartment,4)
    val physicsLaboratory10 = SubjectDegree("25","Laboratorio Física","LFIS10","16",false,true,false,1200, listOf(lFis),physicsDepartment,4)

    val ipLaboratory1 = SubjectDegree("26","Laboratorio Introducción a la programación","LIP1","1",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory2 = SubjectDegree("27","Laboratorio Introducción a la programación","LIP2","2",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory3 = SubjectDegree("28","Laboratorio Introducción a la programación","LIP3","3",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory4 = SubjectDegree("29","Laboratorio Introducción a la programación","LIP4","4",false,true,false,1080, listOf(laboratorio1),softwareDepartment,0)
    val ipLaboratory5 = SubjectDegree("30","Laboratorio Introducción a la programación","LIP5","5",false,true,false,1080, listOf(laboratorio1),softwareDepartment,0)
    val ipLaboratory6 = SubjectDegree("31","Laboratorio Introducción a la programación","LIP6","6",false,true,false,1080, listOf(laboratorio1),softwareDepartment,0)
    val ipLaboratory7 = SubjectDegree("32","Laboratorio Introducción a la programación","LIP7","7",false,true,false,1080, listOf(novell),softwareDepartment,0)
    val ipLaboratory8 = SubjectDegree("33","Laboratorio Introducción a la programación","LIP8","8",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory9 = SubjectDegree("34","Laboratorio Introducción a la programación","LIP9","9",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory10 = SubjectDegree("35","Laboratorio Introducción a la programación","LIP10","10",false,true,false,1080, listOf(sala1),softwareDepartment,0)
    val ipLaboratory11 = SubjectDegree("36","Laboratorio Introducción a la programación","LIP11","11",false,true,false,1080, listOf(laboratorio1),softwareDepartment,0)
    val ipLaboratory12 = SubjectDegree("37","Laboratorio Introducción a la programación","LIP12","12",false,true,false,1080, listOf(laboratorio1),softwareDepartment,0)

    val tcLaboratory1 = SubjectDegree("38","Laboratorio Tecnología de computadores","LTC1","1",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory2 = SubjectDegree("39","Laboratorio Tecnología de computadores","LTC2","2",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory3 = SubjectDegree("40","Laboratorio Tecnología de computadores","LTC3","3",false,true,false,1080, listOf(labC3b),computerDepartment,1)
    val tcLaboratory4 = SubjectDegree("41","Laboratorio Tecnología de computadores","LTC4","4",false,true,false,1080, listOf(labC3b),computerDepartment,1)
    val tcLaboratory5 = SubjectDegree("42","Laboratorio Tecnología de computadores","LTC5","5",false,true,false,1080, listOf(labC3b),computerDepartment,1)
    val tcLaboratory6 = SubjectDegree("43","Laboratorio Tecnología de computadores","LTC6","6",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory7 = SubjectDegree("44","Laboratorio Tecnología de computadores","LTC7","7",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory8 = SubjectDegree("45","Laboratorio Tecnología de computadores","LTC8","8",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory9 = SubjectDegree("46","Laboratorio Tecnología de computadores","LTC9","9",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory10 = SubjectDegree("47","Laboratorio Tecnología de computadores","LTC10","10",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory11 = SubjectDegree("48","Laboratorio Tecnología de computadores","LTC11","11",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory12 = SubjectDegree("49","Laboratorio Tecnología de computadores","LTC12","12",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory13 = SubjectDegree("59","Laboratorio Tecnología de computadores","LTC13","13",false,true,false,1080, listOf(labDigSis),computerDepartment,1)
    val tcLaboratory14 = SubjectDegree("51","Laboratorio Tecnología de computadores","LTC14","14",false,true,false,1080, listOf(labDigSis),computerDepartment,1)

    val algebra1 = SubjectDegree("52","Algebra","AL","A",false,false,false,60, listOf(c1),mathematicsDepartment,2)
    val algebra2 = SubjectDegree("53","Algebra","AL","B",false,false,false,60, listOf(c2),mathematicsDepartment,2)
    val algebraEnglish = SubjectDegree("54","Algebra","ALE","",false,false,true,60, listOf(c6),mathematicsDepartment,2)
    val algebraEnglishSeminary = SubjectDegree("55","Algebra","SALE","",true,false,true,60, listOf(c4),mathematicsDepartment,2)

    val calculus1 = SubjectDegree("56","Cálculo","CAL","A",false,false,false,60, listOf(c1),mathematicsDepartment,3)
    val calculus2 = SubjectDegree("57","Cálculo","CAL","B",false,false,false,60, listOf(c2),mathematicsDepartment,3)

    val physics1 = SubjectDegree("58","Física","FIS","A",false,false,false,1200, listOf(c1),physicsDepartment,4)
    val physics2 = SubjectDegree("59","Física","FIS","B",false,false,false,1200, listOf(c2),physicsDepartment,4)

    val ip1 = SubjectDegree("60","Introducción a la programación","IP","A",false,false,false,1080, listOf(c1),softwareDepartment,0)
    val ip2 = SubjectDegree("61","Introducción a la programación","IP","B",false,false,false,1080, listOf(c2),softwareDepartment,0)

    val tc1 = SubjectDegree("62","Tecnología de computadores","TC","A",false,false,false,1080, listOf(c1),computerDepartment,1)
    val tc2 = SubjectDegree("63","Tecnología de computadores","TC","B",false,false,false,1080, listOf(c2),computerDepartment,1)

    val schedule = ScheduleDegree(degree, "2021-2022", SemesterDegree(num = 0,
            subjectsInSemester = listOf(
                    listOf(
                            /*8:30*/listOf(null,null,null,null,null,null,physicsLaboratory1,null,null, null,null,null,null,null,null),
                            /*9:00*/listOf(null,null,null,null,null,null,physicsLaboratory1,null,null,ipLaboratory1,ipLaboratory4,ipLaboratory7,null,null,tcLaboratory3),
                            /*9:30*/listOf(algebraSeminary1,null,null,calculusSeminary1,null,null,physicsLaboratory1,null,null,ipLaboratory1,ipLaboratory4,ipLaboratory7,null,null,tcLaboratory3),
                            /*10:00*/listOf(algebraSeminary1,null,null,calculusSeminary1,null,null,physicsLaboratory1,null,null,ipLaboratory1,ipLaboratory4,ipLaboratory7,null,null,tcLaboratory3),
                            /*10:30*/listOf(algebraSeminary2,null,null,calculusSeminary2,null,null,physicsLaboratory2,null,null,ipLaboratory2,ipLaboratory5,null,null,tcLaboratory1,tcLaboratory4),
                            /*11:00*/listOf(algebraSeminary2,null,null,calculusSeminary2,null,null,physicsLaboratory2,null,null,ipLaboratory2,ipLaboratory5,null,null,tcLaboratory1,tcLaboratory4),
                            /*11:30*/listOf(algebraSeminary3,null,null,calculusSeminary3,null,null,physicsLaboratory2,null,null,ipLaboratory2,ipLaboratory5,null,null,tcLaboratory1,tcLaboratory4),
                            /*12:00*/listOf(algebraSeminary3,null,null,calculusSeminary3,null,null,physicsLaboratory2,null,null,ipLaboratory3,ipLaboratory6,null,null,tcLaboratory2,tcLaboratory5),
                            /*12:30*/listOf(algebraSeminary4,null,null,calculusSeminary4,null,null,physicsLaboratory3,null,null,ipLaboratory3,ipLaboratory6,null,null,tcLaboratory2,tcLaboratory5),
                            /*13:00*/listOf(algebraSeminary4,null,null,calculusSeminary4,null,null,physicsLaboratory3,null,null,ipLaboratory3,ipLaboratory6,null,null,tcLaboratory2,tcLaboratory5),
                            /*13:30*/listOf(null,null,null,null,null,null,physicsLaboratory3,null,null,null,null,null,null,null,null),
                            /*14:00*/listOf(null,null,null,null,null,null,physicsLaboratory3,null,null,null,null,null,null,null,null),
                            /*15:30*/listOf(null,null,null,null,null,null,physicsLaboratory4,null,null,ipLaboratory8,ipLaboratory11,null,null,tcLaboratory6,null),
                            /*16:00*/listOf(null,null,null,null,null,null,physicsLaboratory4,null,null,ipLaboratory8,ipLaboratory11,null,null,tcLaboratory6,null),
                            /*16:30*/listOf(null,null,null,null,null,null,physicsLaboratory4,null,null,ipLaboratory8,ipLaboratory11,null,null,tcLaboratory6,null),
                            /*17:00*/listOf(null,null,null,null,null,null,physicsLaboratory4,null,null,ipLaboratory9,ipLaboratory12,null,null,tcLaboratory7,null),
                            /*17:30*/listOf(null,null,null,null,null,null,physicsLaboratory5,null,null,ipLaboratory9,ipLaboratory12,null,null,tcLaboratory7,null),
                            /*18:00*/listOf(null,null,null,null,null,null,physicsLaboratory5,null,null,ipLaboratory9,ipLaboratory12,null,null,tcLaboratory7,null),
                            /*18:30*/listOf(null,null,null,null,null,null,physicsLaboratory5,null,null,ipLaboratory10,null,null,null,null,null),
                            /*19:00*/listOf(null,null,null,null,null,null,physicsLaboratory5,null,null,ipLaboratory10,null,null,null,null,null),
                            /*19:30*/listOf(null,null,null,null,null,null,null,null,null,ipLaboratory10,null,null,null,null,null),
                            /*20:00*/listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            /*20:30*/listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            /*21:00*/listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory6,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory6,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory6,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory6,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory7,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory7,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory7,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory7,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory8,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory8,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory8,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory9,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory9,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory9,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics1,null,null,ip2,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physics2,null,null,ip1,null,null,null,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra2,algebraEnglish,null,null,null,null,null,null,null,null,null,null,tc1,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(algebra1,null,null,calculus2,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,calculus1,null,null,null,null,null,null,null,null,tc2,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory10,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory10,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory10,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory11,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory11,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,tcLaboratory11,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
                            ),
                    listOf(
                            listOf(null,null,null,null,null,null,physicsLaboratory8,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory8,null,null,null,null,null,tcLaboratory12,null,null),
                            listOf(algebraSeminary5,algebraEnglishSeminary,null,null,null,null,physicsLaboratory8,null,null,null,null,null,tcLaboratory12,null,null),
                            listOf(algebraSeminary5,algebraEnglishSeminary,null,null,null,null,physicsLaboratory8,null,null,null,null,null,tcLaboratory12,null,null),
                            listOf(algebraSeminary6,null,null,calculusSeminary5,null,null,physicsLaboratory9,null,null,null,null,null,tcLaboratory13,null,null),
                            listOf(algebraSeminary6,null,null,calculusSeminary5,null,null,physicsLaboratory9,null,null,null,null,null,tcLaboratory13,null,null),
                            listOf(algebraSeminary7,null,null,calculusSeminary6,null,null,physicsLaboratory9,null,null,null,null,null,tcLaboratory13,null,null),
                            listOf(algebraSeminary7,null,null,calculusSeminary6,null,null,physicsLaboratory9,null,null,null,null,null,tcLaboratory14,null,null),
                            listOf(algebraSeminary8,null,null,calculusSeminary7,null,null,physicsLaboratory10,null,null,null,null,null,tcLaboratory14,null,null),
                            listOf(algebraSeminary8,null,null,calculusSeminary7,null,null,physicsLaboratory10,null,null,null,null,null,tcLaboratory14,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory10,null,null,null,null,null,null,null,null),
                            listOf(null,null,null,null,null,null,physicsLaboratory10,null,null,null,null,null,null,null,null),
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