package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.dto.UserDto;
import com.example.studentsmanagementapi.exceptions.*;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, BookRepository bookRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.courseRepository = courseRepository;
        this.modelMapper=modelMapper;
    }

    public List<User> getAll(){
        return  this.userRepository.findAll();
    }


    public void addUser(UserDto user){

        boolean userExists= this.userRepository.selectedEmailExists(user.getEmail()).isPresent();

        if(userExists){
            throw new UserExistsException("User exists");
        }
        this.userRepository.save(new User(user.getName(),user.getEmail(),user.getPassword()));
    }

    @Transactional
    public void deleteUser(Long id){
        Boolean userExists=this.userRepository.selectedIdExists(id).isEmpty();

        if(userExists){
            throw new UserNotFoundException(
                    "User not found"
            );
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(UserDto userDto) {

        Boolean userExists = this.userRepository.selectedEmailExists(userDto.getEmail()).isPresent();

        if (!userExists) {
            throw new UserNotFoundException(
                    "User not found"
            );
        }

        User userHelper= this.userRepository.selectedEmailExists(userDto.getEmail()).get();

        this.userRepository.findById(userHelper.getId()).map(person -> {
            person.setEmail(userDto.getEmail());
            person.setEmail(userDto.getEmail());
            person.setPassword(userDto.getPassword());

            return userRepository.save(person);
        });
    }

    @Transactional
    public void addCourseForUser(Long userId, Long courseId){

        boolean userExists=this.userRepository.findById(userId).isPresent();
        if(!userExists){
            throw new UserNotFoundException(
                    "The user does not exist"
            );
        }
        boolean exists=this.courseRepository.findById(courseId).isPresent();
        if(!exists){
              throw new CourseNotFoundException(
                      "Course does not exists");
        }

        User actualUser=this.userRepository.findById(userId).get();
        Course actualCourse=this.courseRepository.findById(courseId).get();

        if(actualUser.getEnrolledCourses().contains(actualCourse)){
            throw new CourseExistsException("You are already enrolled to this course");
        }

        actualUser.addCourse(actualCourse);

        userRepository.save(actualUser);
        courseRepository.save(actualCourse);

        }

    @Transactional
    public void deleteCourseForUser(Long userId, Long courseId){

        boolean userExists=this.userRepository.findById(userId).isPresent();
        if(!userExists){
            throw new UserNotFoundException(
                    "The user does not exist"
            );
        }
        boolean exists=this.courseRepository.findById(courseId).isPresent();
        if(!exists){
            throw new CourseNotFoundException(
                    "Course does not exists");
        }

        User actualUser=this.userRepository.findById(userId).get();
        Course actualCourse=this.courseRepository.findById(courseId).get();

        if(!actualUser.getEnrolledCourses().contains(actualCourse)){
            throw new CourseNotFoundException("You are not enrolled to this course");
        }
        actualUser.removeCourse(actualCourse);

        userRepository.save(actualUser);
        courseRepository.save(actualCourse);

    }

    @Transactional
    public void addBookForUser(Long userId, Long bookId){
        boolean userExists=this.userRepository.findById(userId).isPresent();
        if(!userExists){
            throw new UserNotFoundException(
                    "The user does not exist"
            );
        }

        boolean exists=this.bookRepository.findById(bookId).isPresent();
        if(!exists){
            throw new BookNotFoundException(
                    "The book does exist"
            );
        }

        User actualUser=this.userRepository.findById(userId).get();
        Book actualBook=this.bookRepository.findById(bookId).get();

        if(actualUser.getBooks().contains(actualBook)){
            throw new BookExistsException("You already have this book");
        }

        actualUser.addBook(actualBook);

        userRepository.save(actualUser);
    }

    @Transactional
    public void deleteBookForUser(Long userId, Long bookId){

        boolean userExists=this.userRepository.findById(userId).isPresent();
        if(!userExists){
            throw new UserNotFoundException(
                    "The user does not exist"
            );
        }

        boolean exists=this.bookRepository.findById(bookId).isPresent();
        if(!exists){
            throw new BookNotFoundException(
                    "The book does not exist"
            );
        }

        User actualUser=this.userRepository.findById(userId).get();
        Book actualBook=this.bookRepository.findById(bookId).get();
        if(!actualUser.getBooks().contains(actualBook)){
            throw new BookNotFoundException("You don't have this book");
        }
        actualUser.removeBook(actualBook);

        actualBook.setUser(null);
        bookRepository.save(actualBook);

    }

    @Transactional
    public User getUserById(Long id){
        if(this.userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(
                    "User not found "
            );
        }
        return this.userRepository.findById(id).get();
    }


    @Transactional
    public Optional<User> searchUser(String email, String password){
        Optional<User> user=userRepository.selectedEmailExists(email);
        if(user.isEmpty()){
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        if(!user.get().getPassword().equals(password)){
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

        return user;
    }

    @Transactional
    public Optional<Long> getIdFromEmail(String email){

        Optional<Long> id=userRepository.findIdByUserName(email);
        if(id.isEmpty()){
            throw new InvalidEmailOrPasswordException("The email does not exist");
        }

        return id;
    }

    }








