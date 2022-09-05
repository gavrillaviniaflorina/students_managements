//package com.example.studentsmanagementapi.web;
//
//import com.example.studentsmanagementapi.StudentsManagementApiApplication;
//import com.example.studentsmanagementapi.model.User;
//import com.example.studentsmanagementapi.repository.BookRepository;
//import com.example.studentsmanagementapi.repository.UserRepository;
//import com.example.studentsmanagementapi.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@TestPropertySource(
//        locations = "classpath:application-it.properties"
//)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = StudentsManagementApiApplication.class)
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @MockBean
//    private UserRepository movieRepositoryMock;
//
//    @MockBean
//    private UserService movieServiceMock;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getUsers() throws Exception {
//
//        List<User> users=new ArrayList<>();
//        User user=new User("test1","autor1","gen1");
//        users.add(user);
//        when(movieServiceMock.getAll()).thenReturn(users);
//
//        ObjectMapper mapper=new ObjectMapper();
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(mapper.writeValueAsString(users)));
//
//    }
//}