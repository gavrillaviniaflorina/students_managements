import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.sass']
})
export class CourseComponent implements OnInit, OnDestroy {
  userId:number=4;
  id:string="";
  isEnrolled=false;

  @Input()course:Course={
    id:Math.round( Math.random()*1000),
    name:"",
    departament:""
  }

  private subscription!: Subscription;

  constructor(private courseService:CourseService, private userService:UserService) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.getEnrolledCoursesForUser(this.userId).subscribe(response=>{  
      response.forEach(e=>{
           if(this.course.id==e.id){
             this.isEnrolled=true;           
           }        
      })     
     });
  }

}
