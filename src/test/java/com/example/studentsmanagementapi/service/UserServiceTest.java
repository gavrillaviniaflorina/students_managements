package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.exceptions.UserNotFoundException;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void ItShouldDeleteUser(){

        Faker faker=new Faker();
        User person=new User("lavinia","lavinia.Fay@mycode.edu","ceva");
        person.setId(1L);

        doReturn(Optional.of(person)).when(userRepository).selectedIdExists(1l);
        underTest.deleteUser(person.getId());
        BDDMockito.then(userRepository).should().deleteById(person.getId());
    }

    @Test
    void ItShouldThrowAnExceptonWhenDeleting(){

        Faker faker=new Faker();
        User user=new User("lavinia","lavinia.Fay@mycode.edu","ceva");
        user.setId(1L);

        doReturn(Optional.empty()).when(userRepository).selectedIdExists(1l);

        assertThatThrownBy(()->underTest.deleteUser(user.getId())).isInstanceOf(UserNotFoundException.class).hasMessageContaining("User not found");
    }

   // @Test
//    void ItShouldUpdatePerson(){
//        Faker faker=new Faker();
//
//        UserDto user=new UserDto("lavinia","lavinia.Fay@mycode.edu","ceva");
//
//        User userHelper=new User(user.getName(),user.getEmail(),user.getPassword());
//        userHelper.setId(1L);
//        doReturn(Optional.of(userHelper)).when(personRepository).selectedIdExists(1L);
//        doReturn(Optional.of(userHelper)).when(personRepository).findById(1L);
//        underTest.updateUser(user);
//        then(personRepository).should().save(personArgumentCaptor.capture());
//
//        User userCapture=personArgumentCaptor.getValue();
//
//        assertThat(userCapture).isEqualTo(userHelper);
//
//    }

//    @Test
//    void itShouldNotUpdatePerson(){
//        Faker faker=new Faker();
//
//
//        UserDto user=new UserDto("lavinia","lavinia.Fay@mycode.edu","ceva");
//        User userHelper=new User(user.getName(),user.getEmail(),user.getPassword());
//        userHelper.setId(1L);
//
//        doReturn(Optional.empty()).when(personRepository).selectedIdExists(1L);
//        doReturn(Optional.empty()).when(personRepository).findById(1l);
//        assertThatThrownBy(()->underTest.updateUser(user)).isInstanceOf(UserNotFoundException.class).hasMessageContaining("User not found");
//
//    }
}