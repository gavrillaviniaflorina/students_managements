package com.example.studentsmanagementapi.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(

        locations = "classpath:application-test.properties"
)
class UserRepositoryTest {

}