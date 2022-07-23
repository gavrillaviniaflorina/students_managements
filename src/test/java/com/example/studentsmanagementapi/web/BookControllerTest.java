package com.example.studentsmanagementapi.web;


import com.example.studentsmanagementapi.dto.BookDto;
import com.example.studentsmanagementapi.model.Book;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.example.studentsmanagementapi.service.BookService;
import com.example.studentsmanagementapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {







}