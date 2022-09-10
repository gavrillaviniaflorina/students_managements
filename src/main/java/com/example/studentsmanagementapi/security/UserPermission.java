package com.example.studentsmanagementapi.security;

import lombok.AllArgsConstructor;


    @AllArgsConstructor
    public enum UserPermission{
        COURSE_READ("course:read"),
        COURSE_WRITE("course:write"),
        BOOK_READ("book:read"),
        BOOK_WRITE("book:write"),
        USER_READ("user:write"),
        USER_WRITE("user:write");
        private String permission;
        public String getPermission(){ return permission;}

    }

