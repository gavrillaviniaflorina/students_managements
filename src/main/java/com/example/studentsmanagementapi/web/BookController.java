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

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book){
        this.bookService.addBook(book);
        return  new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book =this.bookService.getBookById(id);
        this.bookService.deleteBook(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PutMapping("updateBook")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        this.bookService.updateBook(bookDto);
        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }

    @GetMapping("/findBookById/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        return new ResponseEntity<>(this.bookService.getBookById(id), HttpStatus.OK);
    }
}
