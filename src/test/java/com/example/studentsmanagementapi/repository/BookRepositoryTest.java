package com.example.studentsmanagementapi.repository;

import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(

        locations = "classpath:application-test.properties"
)
class BookRepositoryTest {
    @Autowired
    private BookRepository underTest;


    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfTheNameDoesNotExist(){

        Optional<Book> expect=underTest.selectedNameExists("aa");

        assertThat(expect).isEmpty();
    }

}