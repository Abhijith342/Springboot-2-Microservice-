package com.student.student.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.student.app.model.Student;

@Repository

public interface StudentRepository extends JpaRepository<Student,Integer>{

    
}
