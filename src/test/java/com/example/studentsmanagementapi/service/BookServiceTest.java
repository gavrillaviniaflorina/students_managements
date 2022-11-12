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
                BookDto bookDto=new BookDto("carte", date,null,"");
                doReturn(Optional.empty()).when(bookRepository).selectedNameExists(bookDto.getBook_name());
                underTest.addBook(bookDto);

                BDDMockito.then(bookRepository).should().save(bookArgumentCaptor.capture());
                Book bookSaved=bookArgumentCaptor.getValue();
                BookDto bookDto1=new BookDto(bookSaved.getBook_name(), bookSaved.getCreated_at(), null,"");
                assertThat(bookDto1).isEqualTo(bookDto);
                }

    @Test
        void ItShouldThrowAnExceptonWhenAddingABook(){
        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        BookDto bookDto=new BookDto("carte", date,null, "");
        doReturn(Optional.of(bookDto)).when(bookRepository).selectedNameExists(bookDto.getBook_name());
        assertThatThrownBy(()->underTest.addBook(bookDto)).isInstanceOf(BookExistsException.class).hasMessageContaining("The book already exists");
            }

    @Test
        void ItShouldDeleteBook(){

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
               Book book=new Book("lavinia", date,"");
                book.setId(1L);

                doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
                underTest.deleteBook(book.getId());
                BDDMockito.then(bookRepository).should().deleteById(book.getId());
                }

    @Test
        void ItShouldThrowAnExceptonWhenDeletingBook(){

        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book=new Book("lavinia", date,"");
        book.setId(1L);

        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

                assertThatThrownBy(()->underTest.deleteBook(book.getId())).isInstanceOf(BookNotFoundException.class).hasMessageContaining("The book does not exist");
            }

    @Test
        void ItShouldUpdateBook(){
                Faker faker=new Faker();

        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        BookDto book=new BookDto("lavinia", date, null,"");

        Book bookHelper=new Book(book.getBook_name(), book.getCreated_at(),"");
                bookHelper.setId(1L);
                doReturn(Optional.of(bookHelper)).when(bookRepository).selectedNameExists(book.getBook_name());
                doReturn(Optional.of(bookHelper)).when(bookRepository).findById(1L);
                underTest.updateBook(book);
                then(bookRepository).should().save(bookArgumentCaptor.capture());

                Book bookCaptor=bookArgumentCaptor.getValue();

                assertThat(bookCaptor).isEqualTo(bookHelper);

                }

    @Test
        void ItShouldThrowAnExceptionWhenUpdatingABook(){

        Faker faker=new Faker();

        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        BookDto book=new BookDto("lavinia", date, null,"");

        Book bookHelper=new Book(book.getBook_name(), book.getCreated_at(),"");
        bookHelper.setId(1L);
        doReturn(Optional.empty()).when(bookRepository).selectedNameExists(book.getBook_name());
        doReturn(Optional.empty()).when(bookRepository).findById(1L);
                assertThatThrownBy(()->underTest.updateBook(book)).isInstanceOf(BookNotFoundException.class).hasMessageContaining("Book not found");
            }

    @Test
        void ItShouldGetBookById(){
        Faker faker=new Faker();
        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
        Book book=new Book("lavinia", date, "");
        book.setId(1L);

        bookRepository.save(book);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
    }
}