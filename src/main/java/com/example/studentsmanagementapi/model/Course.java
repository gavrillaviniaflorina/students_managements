package com.example.studentsmanagementapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "course")
@Entity(name = "Course")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name="id"
    )

    private Long id;

    @Column(
            name="name",
            columnDefinition = "TEXT"
    )
    @NotEmpty
    private String name;


    @Column(
            name="departament",
            columnDefinition = "TEXT"
    )
    @NotEmpty
    private String departament;

    public Course(String name, String departament) {
        this.name = name;
        this.departament = departament;
    }

    @ManyToMany(mappedBy = "enrolledCourses")
    List<User> users=new ArrayList<>();

    public void addUser(User user){
        this.users.add(user);
    }







}
