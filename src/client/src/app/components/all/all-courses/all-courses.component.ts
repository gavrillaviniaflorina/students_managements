import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Course } from '../../../models/course';
import { CourseService } from '../../../services/course.service';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.sass']
})
export class AllCoursesComponent implements OnInit, OnDestroy {

  public courses:Course[]=[];
  private subscription!: Subscription;

  constructor(
    private courseService:CourseService,
    private router:Router)
    { }

  ngOnDestroy(): void {
   this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.courseService.coursesChanged.subscribe(response=>{
      this.courses=[...response];
    })
  }
}
