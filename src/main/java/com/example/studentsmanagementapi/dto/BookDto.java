package com.example.studentsmanagementapi.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookDto {

    private String book_name;
    private LocalDate created_at;
    private Long user_id;

    public BookDto( String book_name, LocalDate created_at) {

        this.book_name = book_name;
        this.created_at = created_at;
    }

    public BookDto(String book_name, LocalDate created_at, Long user_id) {
        this.book_name = book_name;
        this.created_at = created_at;
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
