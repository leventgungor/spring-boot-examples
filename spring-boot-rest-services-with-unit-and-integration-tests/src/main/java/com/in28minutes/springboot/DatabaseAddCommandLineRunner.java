package com.in28minutes.springboot;

import com.in28minutes.springboot.entity.StudentEntity;
import com.in28minutes.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseAddCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentService studentService;

    @Override
    public void run(String... args) throws Exception {

        addStudents();
    }

    private void addStudents() {


        StudentEntity student1 = new StudentEntity("Student1", "Ahmet Burhan", "History", 2.70);
        StudentEntity student2 = new StudentEntity("Student2", "Selim Sek", "History", 2.48);
        StudentEntity student3 = new StudentEntity("Student3", "Behzat Çınar", "Law", 1.90);
        StudentEntity student4 = new StudentEntity("Student4", "Bahar Songül", "Math", 3.40);

        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);
        studentService.addStudent(student4);

    }

}
