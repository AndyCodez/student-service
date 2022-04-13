package com.andycodez.studentservice;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import com.andycodez.studentservice.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_returnsStudent() throws Exception {
        Student student = new Student(1l,"Kairetu", true, 30);
        given(studentService.getStudentById(anyLong())).willReturn(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("name").value("Kairetu"))
                .andExpect(jsonPath("grade").value(30));
    }
}
