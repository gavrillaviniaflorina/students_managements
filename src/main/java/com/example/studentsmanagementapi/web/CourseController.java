package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/courses")
@RestController
@CrossOrigin
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseController) {
        this.courseService = courseController;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(this.courseService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){
        courseService.addCourse(courseDto);
        Course course= this.courseService.getCourseByName(courseDto.getName());
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        Course course=this.courseService.getCourseById(id);
        this.courseService.deleteCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("updateCourse")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        this.courseService.updateCourse(courseDto);
        return new ResponseEntity<>(courseDto,HttpStatus.OK);
    }



}
