package com.avi.crudDemo.service;


import com.avi.crudDemo.entity.Student;
import com.avi.crudDemo.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){

        this.studentRepository = studentRepository;
    }

    public Student createnewStudent(Student student){

        student.setDeleted(false);

       Student savedStudent =   studentRepository.save(student);

        return savedStudent;
    }

    public Student getStudent(Long id){



        Optional<Student> s1 =  studentRepository.getStudentById(id);

        if(s1.isPresent() && !s1.get().isDeleted()){
            return s1.get();
        }
        return null;
    }

    public  List<Student> getAllStudentsDb(){

        return studentRepository.findAll();

    }

    public Student updateStudent(Long id,Student snew){

        Optional<Student> sold = studentRepository.findById(id);

        if(sold.isPresent() && !sold.get().isDeleted()) {
            sold.get().setName(snew.getName());
            sold.get().setRollno(snew.getRollno());
            sold.get().setAge(snew.getAge());

            studentRepository.save(sold.get());
        }
        return sold.orElseThrow();
    }


    public Student deleteStudent(Long id){

        Optional<Student> s1 = studentRepository.getStudentById(id);

        if(s1.isPresent() && !s1.get().isDeleted()) {

            studentRepository.delete(s1.get());

        }
        return s1.orElseThrow();
    }

    public Boolean softDelete(Long id){

        Optional<Student> s = studentRepository.getStudentById(id);

        if(s.isPresent() && !s.get().isDeleted()){

            Student temp = s.get();
            temp.setDeleted(true);
            studentRepository.save(temp);
        }

        return false;
    }
}
