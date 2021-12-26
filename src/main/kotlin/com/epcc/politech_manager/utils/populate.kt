package com.epcc.politech_manager.utils

import com.epcc.politech_manager.classroom.Classroom
import com.epcc.politech_manager.classroom.Pavilion
import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.department.Department
import com.epcc.politech_manager.subject.Subject

fun createBuildingSchedule(): List<List<List<Subject?>>> {
    val mathematicsClassroom = Classroom("C-1B", Pavilion.CIVIL_WORK, "C-1B", 1)
    val buildingClassroom = Classroom("A-1", Pavilion.ARCHITECTURE, "A-1", 2)
    val designClassroom = Classroom("A-6", Pavilion.ARCHITECTURE, "A-6", 3)

    val mathematicsDepartment = Department("Matemáticas", "MAT", 1)
    val buildingDepartment = Department("Construcción", "CONS", 2)
    val graphicalExpressionDepartment = Department("Expresión gráfica", "EX.GR", 3)
    val economicsDepartment = Department("Economía", "EC", 4)

    val degree = Degree("Grado en Edificación", 8,"2021-2022")

    val calculusSeminary = Subject("CÁLCULO", "SCAL", "A", seminary = true, laboratory = false, english = false, time = 60, classroom = mathematicsClassroom, department = mathematicsDepartment, color = 2, id = 111, semester = 0,degree = degree)
    val calculus = Subject("CÁLCULO", "CAL", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = mathematicsDepartment, color = 2, id =112, semester = 0,degree = degree)
    val materialFundamentals = Subject("FUND MATERIALES", "FMAT", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 1, id = 113, semester = 0,degree = degree)
    val materialFundamentalsLaboratory = Subject("FUND MATERIALES", "LFMAT", "A", seminary = false, laboratory = true, english = false, time = 240, classroom = buildingClassroom, department = buildingDepartment, color = 1, id = 114, semester = 0,degree = degree)
    val graphicalExpressionFundamentals = Subject("FUND EXP GRÁFICA", "FEG", "A", seminary = false, laboratory = false, english = false, time = 120, classroom = designClassroom, department = graphicalExpressionDepartment, color = 0, id = 115, semester = 0,degree = degree)
    val structureFundamentals = Subject("FUND ESTRUCTURAS", "FE", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 3, id = 116, semester = 0,degree = degree)
    val economics = Subject("ECONOMÍA Y EMPRESA", "EE", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = economicsDepartment, color = 4, id = 117, semester = 0,degree = degree)
    val graphicalExpressionFundamentalsSeminary = Subject("FUND EXP GRÁFICA", "SFEG", "A", seminary = true, laboratory = false, english = false, time = 120, classroom = designClassroom, department = graphicalExpressionDepartment, color = 0, id = 118, semester = 0,degree = degree)
    val economicsSeminary = Subject("E. y E.", "SEE", "A", seminary = true, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = economicsDepartment, color = 4, id = 1110, semester = 0,degree = degree)
    val structureFundamentalsLaboratory = Subject("FUND ESTRUCTURAS", "LFE", "A", seminary = false, laboratory = true, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 3, id = 1111, semester = 0,degree = degree)
    val buildingReservation = Subject("RESERVADO \nVISITAS\n A OBRA", "RVO", "", seminary = false, laboratory = false, english = false, time = 360, classroom = buildingClassroom, department = buildingDepartment, color = 5, id = 1112, semester = 0,degree = degree)


    return listOf(
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
}

fun createComputerScienceDegree(): List<List<List<Subject?>>> {
    val degree = Degree( "Grado en Ingeniería informática en ingeniería de computadores", 8,"2021-2022",1)

    val c3 = Classroom("C-3",Pavilion.CENTRAL, "C-3",1)
    //val c1b = Classroom("C-1B",Pavilion.CIVIL_WORK, "C-1B",2)
    val c6 = Classroom("C-6",Pavilion.CENTRAL, "C-6",3)
    val c4 = Classroom("C-4",Pavilion.CENTRAL, "C-4",4)
    //val o5 = Classroom("O-5",Pavilion.CIVIL_WORK, "O-5",5)
    val c1 = Classroom("C-1",Pavilion.CENTRAL, "C-1",6)
    val c2 = Classroom("C-2",Pavilion.CENTRAL, "C-2",7)
    val lFis = Classroom("Laboratorio de física",Pavilion.COMPUTING, "LFIS",8)
    val sala1 = Classroom("Sala 1",Pavilion.COMPUTING, "SALA1",9)
    val laboratorio1 = Classroom("Laboratorio 1",Pavilion.COMPUTING, "LAB1",10)
    val novell = Classroom("Novell",Pavilion.COMPUTING, "NOV",11)
    val labDigSis = Classroom("Lab-Sistemas Digitales",Pavilion.COMPUTING, "LSD",12)
    val labC3b = Classroom("Lab-C-3B",Pavilion.COMPUTING, "LC3B",13)


    val physicsDepartment = Department("Fisica aplicada","FIS",0)
    val mathematicsDepartment = Department("Matemáticas","MAT",1)
    val softwareDepartment = Department("Ingeniería de sistemas informáticos y telemáticos","ISIT",2)
    val computerDepartment = Department("Tecnología de los computares y de las comunicaciones","TCC",3)

    val algebraSeminary1 = Subject("Seminario Algebra","SAL1","1", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 1, semester = 0,degree = degree)
    val algebraSeminary2 = Subject("Seminario Algebra","SAL2","2", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 2, semester = 0,degree = degree)
    val algebraSeminary3 = Subject("Seminario Algebra","SAL3","3", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 3, semester = 0,degree = degree)
    val algebraSeminary4 = Subject("Seminario Algebra","SAL4","4", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 4, semester = 0,degree = degree)
    val algebraSeminary5 = Subject("Seminario Algebra","SAL5","5", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 5, semester = 0,degree = degree)
    val algebraSeminary6 = Subject("Seminario Algebra","SAL6","6", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 6, semester = 0,degree = degree)
    val algebraSeminary7 = Subject("Seminario Algebra","SAL7","7", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 7, semester = 0,degree = degree)
    val algebraSeminary8 = Subject("Seminario Algebra","SAL8","8", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 8, semester = 0,degree = degree)

    val calculusSeminary1 = Subject("Seminario Cálculo","SCAL1","1", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 9, semester = 0,degree = degree)
    val calculusSeminary2 = Subject("Seminario Cálculo","SCAL2","2", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 10, semester = 0,degree = degree)
    val calculusSeminary3 = Subject("Seminario Cálculo","SCAL3","3", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 11, semester = 0,degree = degree)
    val calculusSeminary4 = Subject("Seminario Cálculo","SCAL4","4", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 12, semester = 0,degree = degree)
    val calculusSeminary5 = Subject("Seminario Cálculo","SCAL5","5", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 13, semester = 0,degree = degree)
    val calculusSeminary6 = Subject("Seminario Cálculo","SCAL6","6", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 14, semester = 0,degree = degree)
    val calculusSeminary7 = Subject("Seminario Cálculo","SCAL7","7", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 15, semester = 0,degree = degree)

    val physicsLaboratory1 = Subject("Laboratorio Física","LFIS1","1|2", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 16, semester = 0,degree = degree)
    val physicsLaboratory2 = Subject("Laboratorio Física","LFIS2","3|4", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 17, semester = 0,degree = degree)
    val physicsLaboratory3 = Subject("Laboratorio Física","LFIS3","5|6", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 18, semester = 0,degree = degree)
    val physicsLaboratory4 = Subject("Laboratorio Física","LFIS4","7|8", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 19, semester = 0,degree = degree)
    val physicsLaboratory5 = Subject("Laboratorio Física","LFIS5","9", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 20, semester = 0,degree = degree)
    val physicsLaboratory6 = Subject("Laboratorio Física","LFIS6","10", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 21, semester = 0,degree = degree)
    val physicsLaboratory7 = Subject("Laboratorio Física","LFIS7","11", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 22, semester = 0,degree = degree)
    val physicsLaboratory8 = Subject("Laboratorio Física","LFIS8","12|13", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 23, semester = 0,degree = degree)
    val physicsLaboratory9 = Subject("Laboratorio Física","LFIS9","14|15", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 24, semester = 0,degree = degree)
    val physicsLaboratory10 = Subject("Laboratorio Física","LFIS10","16", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 25, semester = 0,degree = degree)

    val ipLaboratory1 = Subject("Laboratorio Introducción a la programación","LIP1","1", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 26, semester = 0,degree = degree)
    val ipLaboratory2 = Subject("Laboratorio Introducción a la programación","LIP2","2", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 27, semester = 0,degree = degree)
    val ipLaboratory3 = Subject("Laboratorio Introducción a la programación","LIP3","3", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 28, semester = 0,degree = degree)
    val ipLaboratory4 = Subject("Laboratorio Introducción a la programación","LIP4","4", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 29, semester = 0,degree = degree)
    val ipLaboratory5 = Subject("Laboratorio Introducción a la programación","LIP5","5", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 30, semester = 0,degree = degree)
    val ipLaboratory6 = Subject("Laboratorio Introducción a la programación","LIP6","6", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 31, semester = 0,degree = degree)
    val ipLaboratory7 = Subject("Laboratorio Introducción a la programación","LIP7","7", seminary = false, laboratory = true, english = false, time = 1080, classroom = novell, department = softwareDepartment, color = 0, id = 32, semester = 0,degree = degree)
    val ipLaboratory8 = Subject("Laboratorio Introducción a la programación","LIP8","8", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 33, semester = 0,degree = degree)
    val ipLaboratory9 = Subject("Laboratorio Introducción a la programación","LIP9","9", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 34, semester = 0,degree = degree)
    val ipLaboratory10 = Subject("Laboratorio Introducción a la programación","LIP10","10", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 35, semester = 0,degree = degree)
    val ipLaboratory11 = Subject("Laboratorio Introducción a la programación","LIP11","11", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 36, semester = 0,degree = degree)
    val ipLaboratory12 = Subject("Laboratorio Introducción a la programación","LIP12","12", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 37, semester = 0,degree = degree)

    val tcLaboratory1 = Subject("Laboratorio Tecnología de computadores","LTC1","1", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 38, semester = 0,degree = degree)
    val tcLaboratory2 = Subject("Laboratorio Tecnología de computadores","LTC2","2", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 39, semester = 0,degree = degree)
    val tcLaboratory3 = Subject("Laboratorio Tecnología de computadores","LTC3","3", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1, id = 40, semester = 0,degree = degree)
    val tcLaboratory4 = Subject("Laboratorio Tecnología de computadores","LTC4","4", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1, id = 41, semester = 0,degree = degree)
    val tcLaboratory5 = Subject("Laboratorio Tecnología de computadores","LTC5","5", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1, id = 42, semester = 0,degree = degree)
    val tcLaboratory6 = Subject("Laboratorio Tecnología de computadores","LTC6","6", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 43, semester = 0,degree = degree)
    val tcLaboratory7 = Subject("Laboratorio Tecnología de computadores","LTC7","7", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 44, semester = 0,degree = degree)
    val tcLaboratory8 = Subject("Laboratorio Tecnología de computadores","LTC8","8", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 45, semester = 0,degree = degree)
    val tcLaboratory9 = Subject("Laboratorio Tecnología de computadores","LTC9","9", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 46, semester = 0,degree = degree)
    val tcLaboratory10 = Subject("Laboratorio Tecnología de computadores","LTC10","10", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 47, semester = 0,degree = degree)
    val tcLaboratory11 = Subject("Laboratorio Tecnología de computadores","LTC11","11", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 48, semester = 0,degree = degree)
    val tcLaboratory12 = Subject("Laboratorio Tecnología de computadores","LTC12","12", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 49, semester = 0,degree = degree)
    val tcLaboratory13 = Subject("Laboratorio Tecnología de computadores","LTC13","13", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 50, semester = 0,degree = degree)
    val tcLaboratory14 = Subject("Laboratorio Tecnología de computadores","LTC14","14", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1, id = 51, semester = 0,degree = degree)

    val algebra1 = Subject("Algebra","AL","A", seminary = false, laboratory = false, english = false, time = 60, classroom = c1, department = mathematicsDepartment, color = 2, id = 52, semester = 0,degree = degree)
    val algebra2 = Subject("Algebra","AL","B", seminary = false, laboratory = false, english = false, time = 60, classroom = c2, department = mathematicsDepartment, color = 2, id = 53, semester = 0,degree = degree)
    val algebraEnglish = Subject("Algebra","ALE","E", seminary = false, laboratory = false, english = true, time = 60, classroom = c6, department = mathematicsDepartment, color = 2, id = 54, semester = 0,degree = degree)
    val algebraEnglishSeminary = Subject("Algebra","SALE","E", seminary = true, laboratory = false, english = true, time = 60, classroom = c4, department = mathematicsDepartment, color = 2, id = 55, semester = 0,degree = degree)

    val calculus1 = Subject("Cálculo","CAL","A", seminary = false, laboratory = false, english = false, time = 60, classroom = c1, department = mathematicsDepartment, color = 3, id = 56, semester = 0,degree = degree)
    val calculus2 = Subject("Cálculo","CAL","B", seminary = false, laboratory = false, english = false, time = 60, classroom = c2, department = mathematicsDepartment, color = 3, id = 57, semester = 0,degree = degree)

    val physics1 = Subject("Física","FIS","A", seminary = false, laboratory = false, english = false, time = 1200, classroom = c1, department = physicsDepartment, color = 4,id = 58, semester = 0,degree = degree)
    val physics2 = Subject("Física","FIS","B", seminary = false, laboratory = false, english = false, time = 1200, classroom = c2, department = physicsDepartment, color = 4, id = 59, semester = 0,degree = degree)

    val ip1 = Subject("Introducción a la programación","IP","A", seminary = false, laboratory = false, english = false, time = 1080, classroom = c1, department = softwareDepartment, color = 0, id = 60, semester = 0,degree = degree)
    val ip2 = Subject("Introducción a la programación","IP","B", seminary = false, laboratory = false, english = false, time = 1080, classroom = c2, department = softwareDepartment, color = 0, id = 61, semester = 0,degree = degree)

    val tc1 = Subject("Tecnología de computadores","TC","A", seminary = false, laboratory = false, english = false, time = 1080, classroom = c1, department = computerDepartment, color = 1, id = 62, semester = 0,degree = degree)
    val tc2 = Subject("Tecnología de computadores","TC","B", seminary = false, laboratory = false, english = false, time = 1080, classroom = c2, department = computerDepartment, color = 1, id = 63, semester = 0,degree = degree)

    return listOf(
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

}