import { Component, Input, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.sass']
})
export class CourseComponent implements OnInit {
  userId:number=4;
  id:string="";
  isEnrolled=false;

  @Input()course:Course={
    id:0,
    name:"",
    departament:""
  }

  constructor(private courseService:CourseService, private userService:UserService) { }

  ngOnInit(): void {
    this.userService.getEnrolledCoursesForUser(this.userId).subscribe(response=>{  
      response.forEach(e=>{
           if(this.course.id==e.id){
             this.isEnrolled=true;           
           }        
      })  
    
     });
  }

}
