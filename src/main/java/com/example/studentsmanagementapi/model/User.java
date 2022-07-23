package com.example.studentsmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="User")
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )

    @Column(
            name="id"
    )

    private long id;

    @Column(
            name="name",
            nullable = false,
            unique = true
    )
    @NotEmpty
    private String name;

    @Column(
            name="email",
            nullable = false,
            unique = true

    )
    @Email
    @NotEmpty
    private String email;

    @Column(
            name="password",
            nullable = false


    )
    @NotEmpty
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    List<Book> notes=new ArrayList<>();


     @ManyToMany
     @JoinTable(
             name="enrolled_courses",
             joinColumns = @JoinColumn(name="user_id"),
             inverseJoinColumns = @JoinColumn(name="course_id")

     )
     private List<Course> enrolledCourses=new ArrayList<>();

     public void addCourse(Course course){
         enrolledCourses.add(course);
     }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addBook(Book book){
        this.notes.add(book);
        book.setUser(this);
    }




}
