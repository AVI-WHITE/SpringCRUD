package com.avi.crudDemo.repository;


import com.avi.crudDemo.entity.Student;
import com.avi.crudDemo.service.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{


    Optional<Student> getStudentById(Long id);
}


