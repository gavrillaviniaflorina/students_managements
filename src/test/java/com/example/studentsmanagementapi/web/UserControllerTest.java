package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

class UserControllerTest {

    @Mock
    private UserRepository personRepository;
    @Mock
    private BookRepository noteRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> noteIdArgumentCaptor;


}