import { Component, Input, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.sass']
})
export class CourseComponent implements OnInit {

  @Input()course:Course={
    id:0,
    name:"",
    departament:""
  }
  constructor() { }

  ngOnInit(): void {
   
  }

}
