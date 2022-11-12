package com.example.studentsmanagementapi.service;

import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.exceptions.CourseExistsException;
import com.example.studentsmanagementapi.exceptions.CourseNotFoundException;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.repository.CourseRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;

    private CourseService underTest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        underTest = new CourseService(courseRepository);
    }


    @Test
    void itShouldGetAllCourses() {

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            books.add(new Book());
        }

        doReturn(books).when(courseRepository).findAll();
        assertThat(underTest.getAll()).isEqualTo(books);
    }

    @Test
    void isShouldAddCourse() {
        Faker faker = new Faker();
        CourseDto courseDto = new CourseDto("carte", "mate","");
        doReturn(Optional.empty()).when(courseRepository).selectedNameExists(courseDto.getName());
        underTest.addCourse(courseDto);

        BDDMockito.then(courseRepository).should().save(courseArgumentCaptor.capture());
        Course course = courseArgumentCaptor.getValue();
        CourseDto courseDto1 = new CourseDto(course.getName(), course.getDepartament(), "");
        assertThat(courseDto1).isEqualTo(courseDto);
    }

    @Test
    void ItShouldThrowAnExceptonWhenAddingACourse() {
        Faker faker = new Faker();
        CourseDto courseDto = new CourseDto("carte", "mate","");
        doReturn(Optional.of(courseDto)).when(courseRepository).selectedNameExists(courseDto.getName());
        assertThatThrownBy(() -> underTest.addCourse(courseDto)).isInstanceOf(CourseExistsException.class).hasMessageContaining("The course already exists");
    }

    @Test
    void ItShouldDeleteBook() {

        Course course = new Course("carte", "mate","");
        doReturn(Optional.of(course)).when(courseRepository).selectedNameExists(course.getName());
        course.setId(1L);

        doReturn(Optional.of(course)).when(courseRepository).findById(course.getId());
        underTest.deleteCourse(course.getId());
        BDDMockito.then(courseRepository).should().deleteById(course.getId());
    }

    @Test
    void ItShouldThrowAnExceptonWhenDeletingBook() {

        Course course = new Course("carte", "mate" ,"");
        doReturn(Optional.of(course)).when(courseRepository).selectedNameExists(course.getName());
        course.setId(1L);

        doReturn(Optional.empty()).when(courseRepository).findById(course.getId());
        assertThatThrownBy(() -> underTest.deleteCourse(course.getId())).isInstanceOf(CourseNotFoundException.class).hasMessageContaining("Course not found");
    }

    @Test
    void ItShouldUpdateBook() {
        CourseDto course = new CourseDto("carte", "mate","");
        doReturn(Optional.of(course)).when(courseRepository).selectedNameExists(course.getName());

        Course courseHelper = new Course(course.getName(), course.getDepartament(), course.getDescription());
        courseHelper.setId(1L);
        doReturn(Optional.of(courseHelper)).when(courseRepository).selectedNameExists(course.getName());
        doReturn(Optional.of(courseHelper)).when(courseRepository).findById(1L);
        underTest.updateCourse(course);
        then(courseRepository).should().save(courseArgumentCaptor.capture());

        Course courseCaptor = courseArgumentCaptor.getValue();

        assertThat(courseCaptor).isEqualTo(courseHelper);

    }

    @Test
    void ItShouldThrowAnExceptionWhenUpdatingABook() {

        CourseDto course = new CourseDto("carte", "mate","");
        doReturn(Optional.of(course)).when(courseRepository).selectedNameExists(course.getName());

        Course courseHelper = new Course(course.getName(), course.getDepartament(),course.getDescription());
        courseHelper.setId(1L);
        doReturn(Optional.empty()).when(courseRepository).selectedNameExists(course.getName());
        doReturn(Optional.empty()).when(courseRepository).findById(1L);
        assertThatThrownBy(() -> underTest.updateCourse(course)).isInstanceOf(CourseNotFoundException.class).hasMessageContaining("Course not found");
    }

    @Test
    void ItShouldGetCourseById() {
        Faker faker = new Faker();
        LocalDate date = LocalDate.of(faker.number().numberBetween(2010, 2022), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30));
        Course course = new Course("carte", "mate","");
        course.setId(1L);

        courseRepository.save(course);
        doReturn(Optional.of(course)).when(courseRepository).findById(course.getId());
    }
}