package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.exporters.BookPDFExporter;
import com.example.studentsmanagementapi.exporters.CoursePDFExporter;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.service.CourseService;
import com.lowagie.text.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(this.courseService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/addCourse")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){
        courseService.addCourse(courseDto);
        Course course= this.courseService.getCourseByName(courseDto.getName());
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        Course course=this.courseService.getCourseById(id);
        this.courseService.deleteCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("updateCourse")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        this.courseService.updateCourse(courseDto);
        return new ResponseEntity<>(courseDto,HttpStatus.OK);
    }

    @GetMapping("findCourseById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Course> findCourseById(@PathVariable Long id){
       return new ResponseEntity<>(this.courseService.getCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/downloadCoursePDF")
    //@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=courses_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Course> courseList = courseService.getAll();

        CoursePDFExporter exporter = new CoursePDFExporter(courseList);
        exporter.export(response);
    }




}
