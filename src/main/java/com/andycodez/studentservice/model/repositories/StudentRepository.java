package com.andycodez.studentservice.model.repositories;

import com.andycodez.studentservice.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String name);

    @Query(value = "Select * from Student where active = true", nativeQuery = true)
    List<Student> getActiveStudents();
}
