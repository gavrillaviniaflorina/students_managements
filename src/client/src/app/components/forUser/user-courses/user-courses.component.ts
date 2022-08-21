import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-courses',
  templateUrl: './user-courses.component.html',
  styleUrls: ['./user-courses.component.sass']
})
export class UserCoursesComponent implements OnInit {
public courses:Course[]=[];
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.userService.enroledCourses.subscribe(response=>{
      this.courses=[...response];
    })
  }

}
