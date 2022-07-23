package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.dto.UserDto;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/students")
@RestController
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user =this.userService.getUserById(id);
        this.userService.deleteUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
        this.userService.addBook(bookDto);
        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book=this.userService.getBookById(id);
        this.userService.deleteBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);


    }
}
