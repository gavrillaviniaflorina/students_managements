import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../../../models/course';
import { CourseService } from '../../../services/course.service';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.sass']
})
export class AllCoursesComponent implements OnInit {
  public courses:Course[]=[];
  constructor(private courseService:CourseService, private router:Router) { }

  ngOnInit(): void {
    this.courseService.coursesChanged.subscribe(response=>{
      this.courses=[...response];
    })
  }

 

}
