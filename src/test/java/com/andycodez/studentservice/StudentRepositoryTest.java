package com.andycodez.studentservice;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Properties;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        Student savedStudent = this.studentRepository.save(new Student(1, "Dre"));

        Student retrievedStudent = this.studentRepository.getStudentByName("Dre");

        then(retrievedStudent.getId()).isNotNull();
        then(retrievedStudent.getName()).isEqualTo(savedStudent.getName());
    }
}
