package com.example.studentsmanagementapi.repository;


import com.example.studentsmanagementapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book where book_name like ?1",nativeQuery = true)
    Optional<Book> selectedNameExists(String book_name);
}
