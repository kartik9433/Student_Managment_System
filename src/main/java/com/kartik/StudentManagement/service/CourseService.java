package com.kartik.StudentManagement.service;

import com.kartik.StudentManagement.model.Course;
import com.kartik.StudentManagement.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course createCourse(Course course) {
       return courseRepo.save(course);
    }

    public List<Course> getAllCourse() {
        return  courseRepo.findAll();
    }

    public Course getCourseById(Long id) {
       return courseRepo.findById(id).orElseThrow(()->new RuntimeException("course is not found"));
    }

    public Course updateCourse(Long id, Course course) {
        Course course1 = courseRepo.findById(id).orElseThrow(()->
                new RuntimeException("course not  found"));
         course1.setName(course.getName());
         course1.setDescription(course.getDescription());
         course1.setPrice(course.getPrice());
         return  courseRepo.save(course1);
    }

    public void deleteById(Long id) {
        courseRepo.deleteById(id);
    }
}
