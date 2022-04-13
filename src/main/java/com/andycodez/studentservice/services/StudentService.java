package com.andycodez.studentservice.services;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.model.repositories.StudentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable(cacheNames = "students")
    public Student getStudentById(long id) {
        return this.studentRepository.findById(id).orElse(null);
    }
}
