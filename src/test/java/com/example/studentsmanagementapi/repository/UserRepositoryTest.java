package com.example.studentsmanagementapi.repository;

import com.example.studentsmanagementapi.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(

        locations = "classpath:application-test.properties"
)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;


    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfTheEmailExists(){
        String email="lavinia.Fay@mycode.edu";
        User user=new User("Lavinia Gavril","lavinia.Fay@mycode.edu","For a Breath I Tarry");
        underTest.save(user);
        Optional<User> expect=underTest.selectedEmailExists(email);


        assertThat(expect).isNotEmpty();

    }

    @Test
    void itShouldCheckIfTheEmailDoesNotExist(){
        String email="lavinia.Fay@mycode.edu";
        Optional<User> expect=underTest.selectedEmailExists(email);

        assertThat(expect).isEmpty();
    }


    @Test
    void itShouldCheckIfIdExists(){
        User user=new User("Lavinia Gavril","lavinia.Fay@mycode.edu","For a Breath I Tarry");
        underTest.save(user);
        Optional<User> expect=underTest.selectedIdExists(user.getId());

        assertThat(expect).isNotEmpty();

    }

    @Test
    void itShouldCheckIfIdDoesNotExist(){

        Optional<User> expect=underTest.selectedIdExists(1L);

        assertThat(expect).isEmpty();
    }
}