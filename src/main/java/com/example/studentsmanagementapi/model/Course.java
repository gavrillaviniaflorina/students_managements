package com.example.studentsmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public Course(Long id, String name, String departament) {
        this.id = id;
        this.name = name;
        this.departament = departament;
    }

    @ManyToMany(mappedBy = "enrolledCourses",
                fetch = FetchType.EAGER)
    @JsonBackReference
    List<User> users=new ArrayList<>();

    public void addUser(User user){
        this.users.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    @Override
    public String toString(){
        String text="";
        text+=this.name+" "+this.departament;
        return text;
    }


    @Override
    public boolean equals(Object obj){
        Course course=(Course) obj;
        return this.name.equals(course.getName());
    }





}
