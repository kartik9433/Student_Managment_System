package com.kartik.StudentManagement.controller;

import com.kartik.StudentManagement.model.Enrollment;
import com.kartik.StudentManagement.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public Enrollment enrollStudentInCourse(@RequestBody Enrollment  enrollment){
               return enrollmentService.enrollStudentInCourse(enrollment);
    }

    @GetMapping("/all")
    public List<Enrollment>getAllEnrollments(){
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id){
       return enrollmentService.getEnrollmentById(id);
    }

     @PutMapping("/{id}")
    public Enrollment updateEnrollmentById(@PathVariable Long id,
                                           @RequestBody Enrollment updatedenrollment){
        return enrollmentService.updateEnrollmentById(id,updatedenrollment);
     }

     @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id){
         enrollmentService.deleteEnrollment(id);
     }
}
