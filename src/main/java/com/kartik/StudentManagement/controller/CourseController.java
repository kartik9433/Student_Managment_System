package com.kartik.StudentManagement.controller;

import com.kartik.StudentManagement.model.Course;
import com.kartik.StudentManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
   private CourseService courseService;

       @PostMapping("/create")
       public Course createCourse(@RequestBody Course course){
           return courseService.createCourse(course);
       }

       @GetMapping("/courses")
    public List<Course> getAllCourse(){
           return  courseService.getAllCourse();
       }

       @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
             return  courseService.getCourseById(id);
       }

       @PutMapping("/{id}")
       public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
             return courseService.updateCourse(id,course);
       }

       @DeleteMapping("/{id}")
      public void deleteById(@PathVariable Long id){
           courseService.deleteById(id);
       }

}
