package com.andycodez.studentservice;

import com.andycodez.studentservice.exceptions.StudentNotFoundException;
import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import com.andycodez.studentservice.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudentById_forMultipleRequests_isRetrievedFromCache () throws StudentNotFoundException {
        Long id = 123L;
        given(studentRepository.findById(id)).willReturn(Optional.of(new Student(id,"James",true, 80)));

        this.studentService.getStudentById(id);
        this.studentService.getStudentById(id);
        this.studentService.getStudentById(id);

        then(studentRepository).should(times(1)).findById(id);
    }
}
