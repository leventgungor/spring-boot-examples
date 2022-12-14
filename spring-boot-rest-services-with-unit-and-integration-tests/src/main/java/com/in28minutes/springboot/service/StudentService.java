package com.in28minutes.springboot.service;

import java.security.SecureRandom;
import java.util.List;

import com.in28minutes.springboot.entity.StudentEntity;
import com.in28minutes.springboot.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final SecureRandom random = new SecureRandom();

    @Autowired
    private StudentRepo studentRepo;


    public List<StudentEntity> retrieveAllStudents() {
        return studentRepo.findAll();
    }

    public StudentEntity retrieveStudent(String studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    public void addStudent(StudentEntity studentEntity){
        studentRepo.save(studentEntity);
    }

    public void deleteStudent(String studentId) {
        studentRepo.deleteById(studentId);
    }

    public void updateStudentGpa(String studentId, Double gpa){
        StudentEntity studentEntity = studentRepo.findById(studentId).orElseThrow();
        studentEntity.setGpa(gpa);
        studentRepo.save(studentEntity);
    }

}