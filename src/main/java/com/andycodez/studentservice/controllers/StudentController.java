package com.andycodez.studentservice.controllers;

import com.andycodez.studentservice.model.entities.Student;
import com.andycodez.studentservice.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return this.studentService.getStudentById(id);
    }
}
