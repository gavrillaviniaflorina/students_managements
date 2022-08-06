import { Component, Input, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.sass']
})
export class CourseDetailsComponent implements OnInit {

  @Input() course:Course={
    id:0,
    name:"",
    departament: ""
  }
  constructor() { }

  ngOnInit(): void {
  }

}
