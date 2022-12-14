package com.in28minutes.springboot.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.in28minutes.springboot.entity.StudentEntity;
import com.in28minutes.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("getAll")
    public List<StudentEntity> getAllStudents() {
        return studentService.retrieveAllStudents();
    }


}
