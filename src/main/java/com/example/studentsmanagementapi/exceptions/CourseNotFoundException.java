package com.example.studentsmanagementapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
