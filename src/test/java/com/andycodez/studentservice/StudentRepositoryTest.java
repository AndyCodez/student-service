package com.andycodez.studentservice;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        Student savedStudent = testEntityManager.persistFlushFind(new Student("Dre", true, 4));

        Student retrievedStudent = this.studentRepository.getStudentByName("Dre");

        then(retrievedStudent.getId()).isNotNull();
        then(retrievedStudent.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void testGetAverageGradeForActiveStudents_returnsStudentGrade() {
        Student savedStudent1 = testEntityManager.persistFlushFind(new Student("Dre", true, 4));
        Student savedStudent2 = testEntityManager.persistFlushFind(new Student("Brenda", true, 8));
        Student savedStudent3 = testEntityManager.persistFlushFind(new Student("Collins", false, 4));

        List<Student> activeStudents = this.studentRepository.getActiveStudents();
        int total = activeStudents.stream().map(Student::getGrade).reduce(0, Integer::sum);
        Double averageGradeForActiveStudents = (double) (total / 2);
        Double realAverage = (double) ((savedStudent1.getGrade() + savedStudent2.getGrade()) / 2);

        then(averageGradeForActiveStudents).isEqualTo(realAverage);
    }
}
