package com.example.studentsmanagementapi;


import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentsManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsManagementApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository, BookRepository bookRepository, CourseRepository courseRepository
    ){
        return args->{

           Faker faker=new Faker();
//
//
//            for(int i=0;i<10;i++){
//                String firstName=faker.name().firstName();
//                String lastName=faker.name().lastName();
//                String email=String.format("%s.%s@mycode.edu",firstName,lastName);
//                User user=new User(faker.name().lastName(),email,faker.book().author());
//                userRepository.save(user);
//            }
//
//
//          User user=userRepository.findById(17L).get();
//
//            for(int i=0;i<10;i++){
//
//                LocalDate date=LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
//                Book book=new Book(faker.book().title(),date);
//                bookRepository.save(book);
//
//            }
//
//            userRepository.save(user);
//

//            for(int i=0;i<10;i++){
//
//;
//                Course course=new Course(faker.book().title(),faker.book().author());
//
//                user.addCourse(course);
//            }
//
//            userRepository.save(user);
//
//
//
//            for(int i=0;i<10;i++){
//                Course course=new Course(faker.book().title(),faker.book().author());
//
//                courseRepository.save(course);
//            }


        };
    }

}
