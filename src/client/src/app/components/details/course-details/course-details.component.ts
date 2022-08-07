import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.sass']
})
export class CourseDetailsComponent implements OnInit {
  userId:number=2;
  id:string=""
  @Input() course:Course={
    id:0,
    name:"",
    departament: ""
  }
  constructor(private courseService:CourseService, private userService:UserService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{
      this.id=params['id'];
      this.courseService.findCourseById(+this.id).subscribe(response=>{
        this.course=response;
      })
    })
  }

  public JoinCourse(){
      this.userService.addCourseForUser(this.userId,+this.id).subscribe(
        
      )
      console.log("aici");
  }

  


  

}
