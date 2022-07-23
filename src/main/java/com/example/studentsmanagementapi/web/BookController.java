package com.example.studentsmanagementapi.web;



import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/books")
@RestController
@CrossOrigin
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(this.bookService.getAll(), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<BookDto> updateNote(@RequestBody BookDto bookDto){
        this.bookService.updateBook(bookDto);
        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }
}
