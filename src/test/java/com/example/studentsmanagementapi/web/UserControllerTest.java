package com.example.studentsmanagementapi.web;

import com.example.studentsmanagementapi.StudentsManagementApiApplication;
import com.example.studentsmanagementapi.model.User;
import com.example.studentsmanagementapi.repository.BookRepository;
import com.example.studentsmanagementapi.repository.UserRepository;
import com.example.studentsmanagementapi.service.UserService;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentsManagementApiApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserRepository movieRepositoryMock;

    @MockBean
    private UserService movieServiceMock;

   // @Autowired
    //private MockMvc mockMvc;


}