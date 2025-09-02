package com.kartik.StudentManagement.service;

import com.kartik.StudentManagement.model.Student;
import com.kartik.StudentManagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student createStudent(Student student) {
        return  studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
       return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(()->
                new RuntimeException("Student is not found"));
    }

    public void deleteById(Long id) {
        if(!studentRepo.existsById(id)){
             throw  new RuntimeException("their is no Student with this id");
        }
        studentRepo.deleteById(id);
    }

    public Student updateStudent(Long id, Student updatestudent) {
          Student student = studentRepo.findById(id).orElseThrow(()->
                  new RuntimeException("User not found"));

         student.setName(updatestudent.getName());
         student.setEmail(updatestudent.getEmail());
         student.setPhoneNo(updatestudent.getPhoneNo());
         return studentRepo.save(student);
    }

}
