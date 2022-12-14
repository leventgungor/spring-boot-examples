package com.in28minutes.springboot.repository;

import com.in28minutes.springboot.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,String> {
}
