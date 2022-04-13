package com.andycodez.studentservice;

import com.andycodez.studentservice.exceptions.StudentNotFoundException;
import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import com.andycodez.studentservice.services.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Get student by Id returns Student")
    @Test
    void testGetStudentByIdForSavedStudent_returnsStudent() throws StudentNotFoundException {
        Student savedStudent = this.studentRepository.save(new Student("Jeff", true, 50));

        Student retrievedStudent = this.studentService.getStudentById(savedStudent.getId());

        then(savedStudent).isEqualTo(retrievedStudent);
        then(retrievedStudent.getName()).isEqualTo("Jeff");
        then(retrievedStudent.getId()).isNotNull();
    }

    @Test
    void throwStudentNotFoundException_inCaseStudentMissing() {
        Long missingId = 999L;
        Throwable throwable = catchThrowable(() -> this.studentService.getStudentById(missingId));
        then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
