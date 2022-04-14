package com.andycodez.studentservice;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.services.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        Student student = new Student(1l, "Mark", true, 30);
        given(studentService.getStudentById(anyLong())).willReturn(student);
    }
}
