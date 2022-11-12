package com.example.studentsmanagementapi;


import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class StudentsManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsManagementApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BookRepository bookRepository, CourseRepository courseRepository) {
        return args -> {

            User user = new User("lavi","lavinia.gavril@gmail.com","123");
            userRepository.save(user);
            Faker faker=new Faker();
            for(int i=0;i<10;i++){

                Course course=new Course(faker.book().title(),faker.book().author(), faker.educator().course().toLowerCase(Locale.ROOT));

                user.addCourse(course);
            }

            userRepository.save(user);
        };
    }
}
