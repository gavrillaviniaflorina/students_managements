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

    @PutMapping("update")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        this.courseService.updateCourse(courseDto);
        return new ResponseEntity<>(courseDto,HttpStatus.OK);
    }

}
