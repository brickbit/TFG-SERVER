package com.epcc.politech_manager.subject

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class SubjectEntityTest{

    @Autowired
    private val repository: SubjectRepository? = null
    @Test
    fun testSubject() {

    }
}