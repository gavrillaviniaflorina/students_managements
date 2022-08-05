package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.exceptions.BookExistsException;
import com.example.studentsmanagementapi.exceptions.BookNotFoundException;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import org.junit.jupiter.api.Test;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository personRepository;

    @MockBean
    private UserService mockUserService;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    private BookService underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest=new BookService(bookRepository);
    }


    @Test
        void itShouldGetAllUBooks(){

                List<Book> books= new ArrayList<>();
            for(int i=0;i<10;i++){
            books.add(new Book());
            }

            doReturn(books).when(bookRepository).findAll();
            assertThat(underTest.getAll()).isEqualTo(books);
            }

    @Test
        void isShouldAddBook(){
                Faker faker=new Faker();
                LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
                BookDto bookDto=new BookDto("carte", date,null);
                doReturn(Optional.empty()).when(bookRepository).selectedNameExists(bookDto.getBook_name());
                underTest.addBook(bookDto);

                BDDMockito.then(bookRepository).should().save(bookArgumentCaptor.capture());
                Book bookSaved=bookArgumentCaptor.getValue();
                BookDto bookDto1=new BookDto(bookSaved.getBook_name(), bookSaved.getCreated_at(), null);
                assertThat(bookDto1).isEqualTo(bookDto);
                }

    @Test
        void ItShouldThrowAnExceptonWhenAddingABook(){
        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        BookDto bookDto=new BookDto("carte", date,null);
        doReturn(Optional.of(bookDto)).when(bookRepository).selectedNameExists(bookDto.getBook_name());
        assertThatThrownBy(()->underTest.addBook(bookDto)).isInstanceOf(BookExistsException.class).hasMessageContaining("The book already exists");
            }

    @Test
        void ItShouldDeleteBook(){

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
               Book book=new Book("lavinia", date);
                book.setId(1L);

                doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
                underTest.deleteBook(book.getId());
                BDDMockito.then(bookRepository).should().deleteById(book.getId());
                }

    @Test
        void ItShouldThrowAnExceptonWhenDeletingBook(){

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book=new Book("lavinia", date);
        book.setId(1L);

        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

                assertThatThrownBy(()->underTest.deleteBook(book.getId())).isInstanceOf(BookNotFoundException.class).hasMessageContaining("The book does not exist");
            }
//
//    @Test
//        void ItShouldUpdateUser(){
//                Faker faker=new Faker();
//
//                UserDto user=new UserDto("lavinia","lavinia.Fay@mycode.edu","ceva");
//
//                User userHelper=new User(user.getName(),user.getEmail(),user.getPassword());
//                userHelper.setId(1L);
//                doReturn(Optional.of(userHelper)).when(userRepository).selectedEmailExists(user.getEmail());
//                doReturn(Optional.of(userHelper)).when(userRepository).findById(1L);
//                underTest.updateUser(user);
//                BDDMockito.then(userRepository).should().save(userArgumentCaptor.capture());
//
//                User userCapture=userArgumentCaptor.getValue();
//
//                assertThat(userCapture).isEqualTo(userHelper);
//
//                }
//
//    @Test
//        void ItShouldThrowAnExceptionWhenUpdatingAnUser(){
//                UserDto userDto=new UserDto("ceva","as@gmail.com","aqee1");
//                doReturn(Optional.empty()).when(userRepository).selectedEmailExists(userDto.getEmail());
//
//                assertThatThrownBy(()->underTest.updateUser(userDto)).isInstanceOf(UserNotFoundException.class).hasMessageContaining("User not found");
//            }
}