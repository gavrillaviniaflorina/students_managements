package com.example.studentsmanagementapi.service;


import com.example.studentsmanagementapi.dto.CourseDto;
import com.example.studentsmanagementapi.exceptions.CourseNotFoundException;
import com.example.studentsmanagementapi.model.Course;
import com.example.studentsmanagementapi.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll(){
        return  this.courseRepository.findAll();
    }

//    public void addCourse(CourseDto course){
//
//        Boolean exists=this.courseRepository.selectedNameExists(course.getName()).isPresent();
//
//        if(exists){
//            throw  new BadRequestException(
//                    "Course name "+course.getName()+" taken"
//            );
//        }
//        courseRepository.save(new Course(course.getName(),course.getDepartament()));
//    }


    public void deleteCourse(Long id){
        Boolean existsId=this.courseRepository.findById(id).isEmpty();

        if(existsId){
            throw new CourseNotFoundException(
                    "Course not found"
            );
        }
        courseRepository.deleteById(id);
    }

    public void updateCourse(CourseDto updateCourse){


        Boolean exists=this.courseRepository.selectedNameExists(updateCourse.getName()).isPresent();

        if(exists){
            throw new CourseNotFoundException(
                    "Course not found"
            );
        }

        Course courseFound=this.courseRepository.selectedNameExists(updateCourse.getName()).get();
        this.courseRepository.findById(courseFound.getId()).map(course->{
            course.setDepartament(updateCourse.getDepartament());

            return courseRepository.save(course);
        });
    }

}
