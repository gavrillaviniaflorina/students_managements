package com.example.studentsmanagementapi.web;



import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.exporters.BookPDFExporter;
import com.example.studentsmanagementapi.exporters.UserPDFExporter;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.service.BookService;
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

@RequestMapping("api/v1/books")
@RestController
@CrossOrigin
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(this.bookService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book){
        this.bookService.addBook(book);
        return  new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping("deleteBook/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book =this.bookService.getBookById(id);
        this.bookService.deleteBook(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PutMapping("updateBook")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        this.bookService.updateBook(bookDto);
        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }

    @GetMapping("/findBookById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        return new ResponseEntity<>(this.bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("/downloadBookPDF")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=books_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Book> listBooks = bookService.getAll();

        BookPDFExporter exporter = new BookPDFExporter(listBooks);
        exporter.export(response);
    }
}
