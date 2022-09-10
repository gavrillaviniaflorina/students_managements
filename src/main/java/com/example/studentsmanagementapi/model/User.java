package com.example.studentsmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.transaction.Transactional;
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
public class User implements UserDetails {

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


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Book> books=new ArrayList<>();


    @JsonManagedReference
     @ManyToMany(
             cascade = CascadeType.ALL,
             fetch = FetchType.EAGER
     )

     @JoinTable(
             name="enrolled_courses",
             joinColumns = {@JoinColumn(name="user_id")},
             inverseJoinColumns = {@JoinColumn(name="course_id")})
     private List<Course> enrolledCourses=new ArrayList<>();



     public void addCourse(Course course){
         enrolledCourses.add(course);
     }

     public  void removeCourse(Course course){
         enrolledCourses.remove(course);
     }

    public void addBook(Book book){
        this.books.add(book);
        book.setUser(this);
    }

    public void removeBook(Book book){
         this.books.remove(book.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
         return "User{"+"id:"+this.id+
                 ", name:"+this.name+
                 ", email:"+this.email+
                 ", password:"+this.password+
                 "}";
    }

    @Override
    public boolean equals(Object obj){
         User user=(User) obj;
         return this.email.equals(user.getEmail());
    }
}
