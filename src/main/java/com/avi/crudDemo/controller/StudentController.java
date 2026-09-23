package com.avi.crudDemo.controller;

import com.avi.crudDemo.entity.Student;
import com.avi.crudDemo.service.StudentService;
import jakarta.servlet.ServletRequest;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create
    @PostMapping("/create")
    public Student createStudent(@RequestBody  Student student){
       Student createdstudent = studentService.createnewStudent(student);

       return createdstudent;
    }


    //read
    @GetMapping("/read/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){



           Student s1 = studentService.getStudent(id);


        if(s1==null){

            return ResponseEntity.status(404)
                    .body(s1);
        }



        return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(s1);

    }

    @GetMapping("/read/getall")
    public ResponseEntity<List<Student>> getAllStudent(){

        List<Student> s = studentService.getAllStudentsDb();

        return ResponseEntity.status(HttpStatus.OK).body(s);

    }

    //Update

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student snew){


        Student s1 = studentService.updateStudent(id,snew);


        return ResponseEntity.status(200).body(s1);


    }


    //delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){

        Student s1 = studentService.deleteStudent(id);

        return ResponseEntity.status(200).body(s1);

    }

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> softDelete (@PathVariable Long id){

        Boolean isDelete = studentService.softDelete(id);

        return ResponseEntity.ok("Student deleted");
    }


}
