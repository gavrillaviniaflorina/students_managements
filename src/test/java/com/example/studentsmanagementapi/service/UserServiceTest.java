package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.dto.UserDto;
import com.example.studentsmanagementapi.exceptions.BookNotFoundException;
import com.example.studentsmanagementapi.exceptions.UserExistsException;
import com.example.studentsmanagementapi.exceptions.UserNotFoundException;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.doReturn;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CourseRepository courseRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;



    //  @InjectMocks
    private UserService underTest;

    private ModelMapper modelMapper=new ModelMapper();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        underTest=new UserService(userRepository, bookRepository,courseRepository, modelMapper);
    }



    @Test
    void itShouldGetAllUsers(){

        List<User> persons= new ArrayList<>();
        for(int i=0;i<10;i++){
            persons.add(new User());
        }

        doReturn(persons).when(userRepository).findAll();
        assertThat(underTest.getAll()).isEqualTo(persons);
    }

    @Test
    void isShouldAddUser(){
        UserDto user=new UserDto("lavinia","lavi@gmail.com","qw12");
        doReturn(Optional.empty()).when(userRepository).selectedEmailExists(user.getEmail());
        underTest.addUser(user);

        BDDMockito.then(userRepository).should().save(userArgumentCaptor.capture());
        User userSaved=userArgumentCaptor.getValue();
        UserDto userDto=new UserDto(userSaved.getName(),userSaved.getEmail(),userSaved.getPassword());
        assertThat(userDto).isEqualTo(user);
    }

    @Test
    void ItShouldThrowAnExceptonWhenAddingAnUser(){
        UserDto user=new UserDto("lavinia","lavi@gmail.com","qw12");
        doReturn(Optional.of(user)).when(userRepository).selectedEmailExists(user.getEmail());
            assertThatThrownBy(()->underTest.addUser(user)).isInstanceOf(UserExistsException.class).hasMessageContaining("User exists");
    }

    @Test
    void ItShouldDeleteUser(){

        Faker faker=new Faker();
        User person=new User("lavinia","lavinia.Fay@mycode.edu","ceva");
        person.setId(1L);

        doReturn(Optional.of(person)).when(userRepository).selectedIdExists(1l);
        underTest.deleteUser(person.getId());
        BDDMockito.then(userRepository).should().deleteById(person.getId());
    }

    @Test
    void ItShouldThrowAnExceptonWhenDeletingUser(){

        Faker faker=new Faker();
        User user=new User("lavinia","lavinia.Fay@mycode.edu","ceva");
        user.setId(1L);

        doReturn(Optional.empty()).when(userRepository).selectedIdExists(1l);

        assertThatThrownBy(()->underTest.deleteUser(user.getId())).isInstanceOf(UserNotFoundException.class).hasMessageContaining("User not found");
    }

    @Test
    void ItShouldUpdateUser(){
        Faker faker=new Faker();

        UserDto user=new UserDto("lavinia","lavinia.Fay@mycode.edu","ceva");

        User userHelper=new User(user.getName(),user.getEmail(),user.getPassword());
        userHelper.setId(1L);
        doReturn(Optional.of(userHelper)).when(userRepository).selectedEmailExists(user.getEmail());
        doReturn(Optional.of(userHelper)).when(userRepository).findById(1L);
        underTest.updateUser(user);
        BDDMockito.then(userRepository).should().save(userArgumentCaptor.capture());

        User userCapture=userArgumentCaptor.getValue();

        assertThat(userCapture).isEqualTo(userHelper);

    }

    @Test
    void ItShouldThrowAnExceptionWhenUpdatingAnUser(){
        UserDto userDto=new UserDto("ceva","as@gmail.com","aqee1");
        doReturn(Optional.empty()).when(userRepository).selectedEmailExists(userDto.getEmail());

        assertThatThrownBy(()->underTest.updateUser(userDto)).isInstanceOf(UserNotFoundException.class).hasMessageContaining("User not found");
    }

    @Test
    void ItShouldAddBookForUser(){
        User user=new User("ceva","as@gmail.com","aqee1");
        user.setId(1L);
        doReturn(Optional.of(user)).when(userRepository).findById(user.getId());

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book =new Book("a",date);
        book.setId(1L);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());

        underTest.addBookForUser(user.getId(),book.getId());
        BDDMockito.then(userRepository).should().save(userArgumentCaptor.capture());

        User user1=userArgumentCaptor.getValue();

        assertThat(user1).isEqualTo(user);


    }

    @Test
    void ItShouldThrowUserNotFoundExceptionWhenAddingABookForAUser(){
        User user=new User("ceva","as@gmail.com","aqee1");
        user.setId(1L);
        doReturn(Optional.empty()).when(userRepository).findById(user.getId());

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book =new Book("a",date);
        book.setId(1L);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());

        assertThatThrownBy(()->underTest.addBookForUser(user.getId(),book.getId())).isInstanceOf(UserNotFoundException.class).hasMessageContaining("The user does not exist");
    }

    @Test
    void ItShouldThrowBookNotFoundExceptionWhenAddingABookForAUser(){
        User user=new User("ceva","as@gmail.com","aqee1");
        user.setId(1L);
        doReturn(Optional.of(user)).when(userRepository).findById(user.getId());

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book =new Book("a",date);
        book.setId(1L);
        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

        assertThatThrownBy(()->underTest.addBookForUser(user.getId(),book.getId())).isInstanceOf(BookNotFoundException.class).hasMessageContaining("The book does exist");
    }
//
//    @Test
//    void ItShouldDeleteBookForUser(){
//        User user=new User("ceva","as@gmail.com","aqee1");
//        user.setId(1L);
//        doReturn(Optional.of(user)).when(userRepository).findById(user.getId());
//
//        Faker faker=new Faker();
//        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
//        Book book =new Book("a",date);
//        book.setId(1L);
//        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
//
//        underTest.deleteBookForUser(user.getId(),book.getId());
//        BDDMockito.then(bookRepository).should().deleteById(book.getId());
//    }

    @Test
    void ItShouldThrowUserNotFoundExceptionWhenDeletingBookForUser(){
        User user=new User("ceva","as@gmail.com","aqee1");
        user.setId(1L);
        doReturn(Optional.empty()).when(userRepository).findById(user.getId());

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book =new Book("a",date);
        book.setId(1L);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());

        assertThatThrownBy(()->underTest.deleteBookForUser(user.getId(),book.getId())).isInstanceOf(UserNotFoundException.class).hasMessageContaining("The user does not exist");
    }

    @Test
    void ItShouldThrowBookNotFoundExceptionWhenDeletingABookForAUser(){
        User user=new User("ceva","as@gmail.com","aqee1");
        user.setId(1L);
        doReturn(Optional.of(user)).when(userRepository).findById(user.getId());

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book =new Book("a",date);
        book.setId(1L);
        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

        assertThatThrownBy(()->underTest.deleteBookForUser(user.getId(),book.getId())).isInstanceOf(BookNotFoundException.class).hasMessageContaining("The book does not exist");
    }
}