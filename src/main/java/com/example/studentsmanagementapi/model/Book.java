package com.example.studentsmanagementapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "book")
@Entity(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(
            name="books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "books_sequence"
    )
    @Column(
            name="id"
    )

    private Long id;

    @Column(
            name="book_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty
    private String book_name;

    @Column(
            name="created_at",
            nullable = false,
            columnDefinition  ="DATE"
    )

    private LocalDate created_at;

    @Column(
            name="description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="user_id_fk"
            )
    )
    @JsonBackReference
    private User user;

    public Book(String title, LocalDate date, String description) {
        this.book_name=title;
        this.created_at=date;
        this.description=description;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
