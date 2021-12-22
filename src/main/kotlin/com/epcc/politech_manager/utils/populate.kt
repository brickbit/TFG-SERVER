package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.classroom.Classroom
import com.epcc.politech_manager.classroom.Pavilion
import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.department.Department
import com.epcc.politech_manager.utils.SemesterDegree
import com.epcc.politech_manager.subject.Subject

fun createBuildingSchedule(): ScheduleDegree {
    val mathematicsClassroom = Classroom("C-1B", Pavilion.CIVIL_WORK, "C-1B", 1)
    val buildingClassroom = Classroom("A-1", Pavilion.ARCHITECTURE, "A-1", 2)
    val designClassroom = Classroom("A-6", Pavilion.ARCHITECTURE, "A-6", 3)

    val mathematicsDepartment = Department("Matemáticas", "MAT", 1)
    val buildingDepartment = Department("Construcción", "CONS", 2)
    val graphicalExpressionDepartment = Department("Expresión gráfica", "EX.GR", 3)
    val economicsDepartment = Department("Economía", "EC", 4)


    val calculusSeminary = Subject("CÁLCULO", "SCAL", "A", seminary = true, laboratory = false, english = false, time = 60, classroom = mathematicsClassroom, department = mathematicsDepartment, color = 2, 111)
    val calculus = Subject("CÁLCULO", "CAL", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = mathematicsDepartment, color = 2, 112)
    val materialFundamentals = Subject("FUND MATERIALES", "FMAT", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 1, 113)
    val materialFundamentalsLaboratory = Subject("FUND MATERIALES", "LFMAT", "A", seminary = false, laboratory = true, english = false, time = 240, classroom = buildingClassroom, department = buildingDepartment, color = 1, 114)
    val graphicalExpressionFundamentals = Subject("FUND EXP GRÁFICA", "FEG", "A", seminary = false, laboratory = false, english = false, time = 120, classroom = designClassroom, department = graphicalExpressionDepartment, color = 0, 115)
    val structureFundamentals = Subject("FUND ESTRUCTURAS", "FE", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 3, 116)
    val economics = Subject("ECONOMÍA Y EMPRESA", "EE", "A", seminary = false, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = economicsDepartment, color = 4, 117)
    val graphicalExpressionFundamentalsSeminary = Subject("FUND EXP GRÁFICA", "SFEG", "A", seminary = true, laboratory = false, english = false, time = 120, classroom = designClassroom, department = graphicalExpressionDepartment, color = 0, 118)
    val economicsSeminary = Subject("E. y E.", "SEE", "A", seminary = true, laboratory = false, english = false, time = 180, classroom = buildingClassroom, department = economicsDepartment, color = 4, 1110)
    val structureFundamentalsLaboratory = Subject("FUND ESTRUCTURAS", "LFE", "A", seminary = false, laboratory = true, english = false, time = 180, classroom = buildingClassroom, department = buildingDepartment, color = 3, 1111)
    val buildingReservation = Subject("RESERVADO \nVISITAS\n A OBRA", "RVO", "", seminary = false, laboratory = false, english = false, time = 360, classroom = buildingClassroom, department = buildingDepartment, color = 5, 1112)

    val degree = Degree("Grado en Edificación", 8)

    return ScheduleDegree(degree, "2021-2022", SemesterDegree(num = 0,
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
}

fun createComputerScienceDegree(): ScheduleDegree {
    val degree = Degree( "Grado en Ingeniería informática en ingeniería de computadores", 8)

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


    val physicsDepartment = Department("0","Física aplicada",0)
    val mathematicsDepartment = Department("1","Matemáticas",1)
    val softwareDepartment = Department("2","Ingeniería de sistemas informáticos y telemáticos",2)
    val computerDepartment = Department("2","Tecnología de los computares y de las comunicaciones",3)

    val algebraSeminary1 = Subject("Seminario Algebra","SAL1","1", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 1)
    val algebraSeminary2 = Subject("Seminario Algebra","SAL2","2", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 2)
    val algebraSeminary3 = Subject("Seminario Algebra","SAL3","3", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 3)
    val algebraSeminary4 = Subject("Seminario Algebra","SAL4","4", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 4)
    val algebraSeminary5 = Subject("Seminario Algebra","SAL5","5", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 5)
    val algebraSeminary6 = Subject("Seminario Algebra","SAL6","6", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 6)
    val algebraSeminary7 = Subject("Seminario Algebra","SAL7","7", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 7)
    val algebraSeminary8 = Subject("Seminario Algebra","SAL8","8", seminary = true, laboratory = false, english = false, time = 60, classroom = c3, department = mathematicsDepartment, color = 2, id = 8)

    val calculusSeminary1 = Subject("Seminario Cálculo","SCAL1","1", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 9)
    val calculusSeminary2 = Subject("Seminario Cálculo","SCAL2","2", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 10)
    val calculusSeminary3 = Subject("Seminario Cálculo","SCAL3","3", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 11)
    val calculusSeminary4 = Subject("Seminario Cálculo","SCAL4","4", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 12)
    val calculusSeminary5 = Subject("Seminario Cálculo","SCAL5","5", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 13)
    val calculusSeminary6 = Subject("Seminario Cálculo","SCAL6","6", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 14)
    val calculusSeminary7 = Subject("Seminario Cálculo","SCAL7","7", seminary = true, laboratory = false, english = false, time = 60, classroom = c4, department = mathematicsDepartment, color = 3, id = 15)

    val physicsLaboratory1 = Subject("Laboratorio Física","LFIS1","1|2", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 16)
    val physicsLaboratory2 = Subject("Laboratorio Física","LFIS2","3|4", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 17)
    val physicsLaboratory3 = Subject("Laboratorio Física","LFIS3","5|6", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 18)
    val physicsLaboratory4 = Subject("Laboratorio Física","LFIS4","7|8", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 19)
    val physicsLaboratory5 = Subject("Laboratorio Física","LFIS5","9", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 20)
    val physicsLaboratory6 = Subject("Laboratorio Física","LFIS6","10", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 21)
    val physicsLaboratory7 = Subject("Laboratorio Física","LFIS7","11", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 22)
    val physicsLaboratory8 = Subject("Laboratorio Física","LFIS8","12|13", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 23)
    val physicsLaboratory9 = Subject("Laboratorio Física","LFIS9","14|15", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 24)
    val physicsLaboratory10 = Subject("Laboratorio Física","LFIS10","16", seminary = false, laboratory = true, english = false, time = 1200, classroom = lFis, department = physicsDepartment, color = 4, id = 25)

    val ipLaboratory1 = Subject("Laboratorio Introducción a la programación","LIP1","1", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 26)
    val ipLaboratory2 = Subject("Laboratorio Introducción a la programación","LIP2","2", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 27)
    val ipLaboratory3 = Subject("Laboratorio Introducción a la programación","LIP3","3", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 28)
    val ipLaboratory4 = Subject("Laboratorio Introducción a la programación","LIP4","4", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 29)
    val ipLaboratory5 = Subject("Laboratorio Introducción a la programación","LIP5","5", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 30)
    val ipLaboratory6 = Subject("Laboratorio Introducción a la programación","LIP6","6", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 31)
    val ipLaboratory7 = Subject("Laboratorio Introducción a la programación","LIP7","7", seminary = false, laboratory = true, english = false, time = 1080, classroom = novell, department = softwareDepartment, color = 0, id = 32)
    val ipLaboratory8 = Subject("Laboratorio Introducción a la programación","LIP8","8", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 33)
    val ipLaboratory9 = Subject("Laboratorio Introducción a la programación","LIP9","9", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 34)
    val ipLaboratory10 = Subject("Laboratorio Introducción a la programación","LIP10","10", seminary = false, laboratory = true, english = false, time = 1080, classroom = sala1, department = softwareDepartment, color = 0, id = 35)
    val ipLaboratory11 = Subject("Laboratorio Introducción a la programación","LIP11","11", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 36)
    val ipLaboratory12 = Subject("Laboratorio Introducción a la programación","LIP12","12", seminary = false, laboratory = true, english = false, time = 1080, classroom = laboratorio1, department = softwareDepartment, color = 0, id = 37)

    val tcLaboratory1 = Subject("Laboratorio Tecnología de computadores","LTC1","1", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,38)
    val tcLaboratory2 = Subject("Laboratorio Tecnología de computadores","LTC2","2", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,39)
    val tcLaboratory3 = Subject("Laboratorio Tecnología de computadores","LTC3","3", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1,40)
    val tcLaboratory4 = Subject("Laboratorio Tecnología de computadores","LTC4","4", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1,41)
    val tcLaboratory5 = Subject("Laboratorio Tecnología de computadores","LTC5","5", seminary = false, laboratory = true, english = false, time = 1080, classroom = labC3b, department = computerDepartment, color = 1,42)
    val tcLaboratory6 = Subject("Laboratorio Tecnología de computadores","LTC6","6", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,43)
    val tcLaboratory7 = Subject("Laboratorio Tecnología de computadores","LTC7","7", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,44)
    val tcLaboratory8 = Subject("Laboratorio Tecnología de computadores","LTC8","8", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,45)
    val tcLaboratory9 = Subject("Laboratorio Tecnología de computadores","LTC9","9", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,46)
    val tcLaboratory10 = Subject("Laboratorio Tecnología de computadores","LTC10","10", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,47)
    val tcLaboratory11 = Subject("Laboratorio Tecnología de computadores","LTC11","11", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,48)
    val tcLaboratory12 = Subject("Laboratorio Tecnología de computadores","LTC12","12", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,49)
    val tcLaboratory13 = Subject("Laboratorio Tecnología de computadores","LTC13","13", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,50)
    val tcLaboratory14 = Subject("Laboratorio Tecnología de computadores","LTC14","14", seminary = false, laboratory = true, english = false, time = 1080, classroom = labDigSis, department = computerDepartment, color = 1,51)

    val algebra1 = Subject("Algebra","AL","A", seminary = false, laboratory = false, english = false, time = 60, classroom = c1, department = mathematicsDepartment, color = 2,52)
    val algebra2 = Subject("Algebra","AL","B", seminary = false, laboratory = false, english = false, time = 60, classroom = c2, department = mathematicsDepartment, color = 2,53)
    val algebraEnglish = Subject("Algebra","ALE","", seminary = false, laboratory = false, english = true, time = 60, classroom = c6, department = mathematicsDepartment, color = 2,54)
    val algebraEnglishSeminary = Subject("Algebra","SALE","", seminary = true, laboratory = false, english = true, time = 60, classroom = c4, department = mathematicsDepartment, color = 2,55)

    val calculus1 = Subject("Cálculo","CAL","A", seminary = false, laboratory = false, english = false, time = 60, classroom = c1, department = mathematicsDepartment, color = 3,56)
    val calculus2 = Subject("Cálculo","CAL","B", seminary = false, laboratory = false, english = false, time = 60, classroom = c2, department = mathematicsDepartment, color = 3,57)

    val physics1 = Subject("Física","FIS","A", seminary = false, laboratory = false, english = false, time = 1200, classroom = c1, department = physicsDepartment, color = 4,58)
    val physics2 = Subject("Física","FIS","B", seminary = false, laboratory = false, english = false, time = 1200, classroom = c2, department = physicsDepartment, color = 4,59)

    val ip1 = Subject("Introducción a la programación","IP","A", seminary = false, laboratory = false, english = false, time = 1080, classroom = c1, department = softwareDepartment, color = 0,60)
    val ip2 = Subject("Introducción a la programación","IP","B", seminary = false, laboratory = false, english = false, time = 1080, classroom = c2, department = softwareDepartment, color = 0,61)

    val tc1 = Subject("Tecnología de computadores","TC","A", seminary = false, laboratory = false, english = false, time = 1080, classroom = c1, department = computerDepartment, color = 1,62)
    val tc2 = Subject("Tecnología de computadores","TC","B", seminary = false, laboratory = false, english = false, time = 1080, classroom = c2, department = computerDepartment, color = 1,63)

    return ScheduleDegree(degree, "2021-2022", SemesterDegree(num = 0,
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
}