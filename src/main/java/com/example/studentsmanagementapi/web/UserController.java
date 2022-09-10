package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.dto.UserDto;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.service.BookService;
import com.example.studentsmanagementapi.service.CourseService;
import com.example.studentsmanagementapi.exporters.UserPDFExporter;
import com.example.studentsmanagementapi.service.UserService;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("api/v1/students")
@RestController
@CrossOrigin
@Slf4j
public class UserController {

    private UserService userService;

    private BookService bookService;

    private CourseService courseService;

    public UserController(UserService userService, BookService bookService, CourseService courseService) {
        this.userService = userService;
        this.courseService=courseService;
        this.bookService=bookService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        this.userService.addUser(user);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user =this.userService.getUserById(id);
        this.userService.deleteUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping("/addBookForUser/{id}/{bookId}")
    public ResponseEntity<Book> addBook(@PathVariable Long id,@PathVariable Long bookId){
        Book book=this.bookService.getBookById(bookId);
        this.userService.addBookForUser(id, bookId);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping("deleteBookForUser/{id}/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id, @PathVariable Long bookId){
        Book book=this.bookService.getBookById(bookId);
        this.userService.deleteBookForUser(id,bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addCourseForUser/{id}/{courseId}")
    public ResponseEntity<Course> addCourse(@PathVariable Long id, @PathVariable Long courseId){
        Course course= this.courseService.getCourseById(courseId);
        userService.addCourseForUser(id,courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("deleteCourseForUser/{id}/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id, @PathVariable Long courseId){
        Course course=this.courseService.getCourseById(courseId);
        this.userService.deleteCourseForUser(id,courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("getCoursesOfUser/{id}")
    public ResponseEntity<List<Course>> enrolledCoursesForUser(@PathVariable Long id){
        log.info("Rest request for enrolled course");
       User user=this.userService.getUserById(id);
       return new ResponseEntity<>(user.getEnrolledCourses(), HttpStatus.OK);
    }

    @GetMapping("getBooksOfUser/{id}")
    public ResponseEntity<List<Book>> bookedBooksForUser(@PathVariable Long id){
        log.info("Rest request for enrolled course");
        User user=this.userService.getUserById(id);
        return new ResponseEntity<>(user.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/downloadUserPDF")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userService.getAll();

        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
    }

}
