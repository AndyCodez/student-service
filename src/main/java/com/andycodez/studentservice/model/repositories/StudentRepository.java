package com.andycodez.studentservice.model.repositories;

import com.andycodez.studentservice.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String name);
}
