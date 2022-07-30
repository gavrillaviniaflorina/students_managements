package com.example.studentsmanagementapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseExistsException extends RuntimeException{
    public CourseExistsException(String msg) {
        super(msg);
    }
}
