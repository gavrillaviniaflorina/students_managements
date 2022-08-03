package com.example.studentsmanagementapi.service;


import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.exceptions.BookNotFoundException;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public void updateBook(BookDto updateBook){

        Boolean updatedId=this.bookRepository.selectedNameExists(updateBook.getBook_name()).isPresent();

        if(!updatedId){
            throw new BookNotFoundException(
                    "Book not found"
            );
        }
        Book bookFound=this.bookRepository.selectedNameExists(updateBook.getBook_name()).get();
        this.bookRepository.findById(bookFound.getId()).map(book -> {

            book.setBook_name(updateBook.getBook_name());
            book.setCreated_at(updateBook.getCreated_at());

            return bookRepository.save(book);
        });
    }

    public Book getBookById(Long id){
        if(this.bookRepository.findById(id).isEmpty()){
            throw new BookNotFoundException(
                    "Book not found "
            );
        }
        return this.bookRepository.findById(id).get();
    }
}
