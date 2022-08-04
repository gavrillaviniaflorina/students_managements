//package com.example.studentsmanagementapi.service;
//
//import com.example.studentsmanagementapi.dto.BookDto;
//import com.example.studentsmanagementapi.exceptions.BookNotFoundException;
//import com.example.studentsmanagementapi.model.Book;
//import com.example.studentsmanagementapi.repository.BookRepository;
//import com.example.studentsmanagementapi.repository.UserRepository;
//import com.github.javafaker.Faker;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Mockito.doReturn;
//
//class BookServiceTest {
//
//    @Mock
//    private BookRepository noteRepository;
//
//    @Mock
//    private UserRepository personRepository;
//
//    @MockBean
//    private UserService mockUserService;
//
//    @Captor
//    private ArgumentCaptor<Book> bookArgumentCaptor;
//
//    private BookService underTest;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.openMocks(this);
//        underTest=new BookService(noteRepository);
//    }
//
//    @Test
//    void itShouldGetAllNotes(){
//
//        List<Book> books=new ArrayList<>();
//
//        for (int i=0;i<10;i++){
//            books.add(new Book());
//        }
//
//        doReturn(books).when(noteRepository).findAll();
//        assertThat(underTest.getAll()).isEqualTo(books);
//    }
//
//    @Test
//    void itShouldNotUpdateNote(){
//        Faker faker=new Faker();
//
//        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
//
//        BookDto book=new BookDto(faker.book().title() ,date);
//        Book booHelper= new Book(book.getBook_name(),book.getCreated_at());
//        booHelper.setId(1L);
//
//        doReturn(false).when(noteRepository).existsById(1L);
//
//        assertThatThrownBy(()->underTest.updateBook(book)).isInstanceOf(BookNotFoundException.class).hasMessageContaining("Book not found");
//    }
//
//    @Test
//    void itShouldUpdateNote(){
//
//        Faker faker=new Faker();
//
//        LocalDate date= LocalDate.of(faker.number().numberBetween(2010,2022),faker.number().numberBetween(1,12),faker.number().numberBetween(1,30));
//
//        BookDto book=new BookDto(faker.book().title() ,date);
//        Book booHelper= new Book(book.getBook_name(),book.getCreated_at());
//        booHelper.setId(1L);
//
//        doReturn(Optional.of(booHelper)).when(noteRepository).findById(1L);
//        doReturn(Optional.of(booHelper)).when(noteRepository).selectedNameExists(book.getBook_name());
//
//        underTest.updateBook(book);
//
//        then(noteRepository).should().save(bookArgumentCaptor.capture());
//
//        Book noteCapture= bookArgumentCaptor.getValue();
//
//        assertThat(booHelper).isEqualTo(noteCapture);
//
//    }
//
//}