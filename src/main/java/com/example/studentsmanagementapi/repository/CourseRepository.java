package com.example.studentsmanagementapi.repository;

import com.example.studentsmanagementapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select * from course where name like ?1",nativeQuery = true)
    Optional<Course> selectedNameExists(String email);
}
