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

    @Transactional
    public void addUser(UserDto user){

        boolean userExists= this.userRepository.selectedEmailExists(user.getEmail()).isPresent();

        if(userExists){
            throw new UserNotFoundException("User not found");
        }

        User userSaved=this.userRepository.selectedEmailExists(user.getEmail()).get();
        this.userRepository.save(userSaved);
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

        if (userExists) {
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
        if(exists){
            throw new BookExistsException(
                    "The book does exist"
            );
        }

        User actualUser=this.userRepository.findById(userId).get();
        Book actualBook=this.bookRepository.findById(bookId).get();
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
        if(exists){
            throw new BookExistsException(
                    "The book does exist"
            );
        }

        User actualUser=this.userRepository.findById(userId).get();
        Book actualBook=this.bookRepository.findById(bookId).get();
        actualUser.removeBook(actualBook);

        userRepository.save(actualUser);

    }

    public User getUserById(Long id){
        if(this.userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(
                    "User not found "
            );
        }
        return this.userRepository.findById(id).get();
    }

    public Book getBookById(Long id){
        if(this.bookRepository.findById(id).isEmpty()){
            throw new BookNotFoundException(
                    "Book not found "
            );
        }
        return this.bookRepository.findById(id).get();
    }

    public Course getCourseById(Long id){
        if(this.courseRepository.findById(id).isEmpty()){
            throw new CourseExistsException(
                    "Course not found "
            );
        }
        return this.courseRepository.findById(id).get();
    }


//
//    @Transactional
//    public void addBookForUser(UserDto user,BookDto bookDto){
//        boolean userExists=this.userRepository.selectedEmailExists(user.getEmail()).isPresent();
//        if(!userExists){
//            throw new UserNotFoundException(
//                    "The user does not exist"
//            );
//        }
//
//        boolean exists=this.bookRepository.selectedNameExists(bookDto.getBook_name()).isPresent();
//        if(exists){
//            throw new BookExistsException(
//                    "The book does exist"
//            );
//        }
//
//        Optional<User> user=this.userRepository.selectedIdExists(bookDto.getUser_id());
//
//        if(user.isPresent()){
//
//            User userHelper=user.get();
//
//            modelMapper.getConfiguration()
//                    .setMatchingStrategy(MatchingStrategies.LOOSE);
//
//            Book book= new Book();
//            modelMapper.map(bookDto,book);
//            userHelper.addBook(book);
//            userRepository.save(user.get());
//
//        }else{
//
//            throw new UserNotFoundException(
//                    "User not found"
//            );
//        }

    }








