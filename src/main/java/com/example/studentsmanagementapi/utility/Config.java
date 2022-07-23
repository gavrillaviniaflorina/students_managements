package com.example.studentsmanagementapi.utility;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {


    @Bean
    public ModelMapper modelMapper(){

        return  new ModelMapper();
    }


    }


