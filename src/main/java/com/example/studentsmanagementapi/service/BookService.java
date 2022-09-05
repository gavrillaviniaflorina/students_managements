package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.exceptions.BookExistsException;
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


    public void addBook(BookDto book){
        boolean exists=this.bookRepository.selectedNameExists(book.getBook_name()).isPresent();

        if(exists){
            throw new BookExistsException(
                    "The book already exists"
            );
        }
        this.bookRepository.save(new Book(book.getBook_name(),book.getCreated_at(), book.getDescription()));
    }

    public void deleteBook(Long id){
        boolean exists=this.bookRepository.findById(id).isPresent();

        if(!exists){
            throw new BookNotFoundException(
                    "The book does not exist"
            );
        }
        this.bookRepository.deleteById(id);
    }

    public void updateBook(BookDto updateBook){

        Boolean updated=this.bookRepository.selectedNameExists(updateBook.getBook_name()).isPresent();

        if(!updated){
            throw new BookNotFoundException(
                    "Book" +updateBook.getBook_name()+" not found"
            );
        }
        Book bookFound=this.bookRepository.selectedNameExists(updateBook.getBook_name()).get();
        this.bookRepository.findById(bookFound.getId()).map(book -> {

            book.setBook_name(updateBook.getBook_name());
            book.setCreated_at(updateBook.getCreated_at());
            book.setDescription(updateBook.getDescription());

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
