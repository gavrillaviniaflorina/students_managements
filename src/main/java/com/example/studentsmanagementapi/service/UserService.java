package com.example.studentsmanagementapi.service;


import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.dto.UserDto;
import com.example.studentsmanagementapi.exceptions.BookExistsException;
import com.example.studentsmanagementapi.exceptions.BookNotFoundException;
import com.example.studentsmanagementapi.exceptions.UserNotFoundException;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

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
            throw new UserNotFoundException("User not found");
        }

        User userSaved=new User(user.getName(),user.getEmail(), user.getPassword());
        this.userRepository.save(userSaved);
    }


    public void deleteUser(Long id){
        Boolean existsId=this.userRepository.selectedIdExists(id).isEmpty();

        if(existsId){
            throw new UserNotFoundException(
                    "User not found"
            );
        }
        userRepository.deleteById(id);
    }


    public void updateUser(UserDto userDto) {


        Boolean exists = this.userRepository.selectedEmailExists(userDto.getEmail()).isPresent();

        if (exists) {
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

//    public void addCourse(CourseDto courseDto){
//      boolean exists=this.courseRepository.selectedNameExists(courseDto.getName()){
//          if(exists){
//              throw new Course
//          }
//        }
//    }

    public void addBook(BookDto bookDto){
        boolean exists=this.bookRepository.selectedNameExists(bookDto.getBook_name()).isPresent();
        if(exists){
            throw new BookExistsException(
                    "The book does exist"
            );
        }

         Optional<User> user=this.userRepository.selectedIdExists(bookDto.getUser_id());

        if(user.isPresent()){

            User userHelper=user.get();

            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.LOOSE);

            Book book= new Book();
            modelMapper.map(bookDto,book);
            userHelper.addBook(book);
            userRepository.save(user.get());

        }else{

            throw new UserNotFoundException(
                    "User not found"
            );
        }

    }

    public void deleteBook(Long id){

        Optional<Book> book=bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);


        }else{
            throw new BookNotFoundException(
                    "Book not found"
            );
        }

    }

    public User getUserById( Long id){
        if(this.userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(
                    "User not found "
            );
        }
        return this.userRepository.findById(id).get();
    }

    public Book getBookById( Long id){
        if(this.bookRepository.findById(id).isEmpty()){
            throw new BookNotFoundException(
                    "Book not found "
            );
        }
        return this.bookRepository.findById(id).get();
    }







}
