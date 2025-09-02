package com.kartik.StudentManagement.service;

import com.kartik.StudentManagement.model.Course;
import com.kartik.StudentManagement.model.Enrollment;
import com.kartik.StudentManagement.model.Student;
import com.kartik.StudentManagement.repo.CourseRepo;
import com.kartik.StudentManagement.repo.EnrollmentRepo;
import com.kartik.StudentManagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentService  {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private StudentRepo studentRepo;

    public Enrollment enrollStudentInCourse(Enrollment enrollment) {
        Student student = studentRepo.findById(enrollment.getStudent().
                      getId()).orElseThrow(()->new RuntimeException("no student found"));
        Course course = courseRepo.findById(enrollment.getCourse().
                getId()).orElseThrow(()->new RuntimeException("no course found"));
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        return  enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
         return enrollmentRepo.findAll();
    }


    public Enrollment getEnrollmentById(Long id) {
             return  enrollmentRepo.findById(id).orElseThrow(()->
                     new RuntimeException("No enrollment found"));
    }

    public Enrollment updateEnrollmentById(Long id, Enrollment updatedenrollment) {
              Enrollment enrollment = enrollmentRepo.findById(id).orElseThrow(()->
                      new RuntimeException("no enrollment"));
              enrollment.setGrade(updatedenrollment.getGrade());
              enrollment.setAttendance(updatedenrollment.getAttendance());
              return   enrollmentRepo.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepo.deleteById(id);
    }


}
