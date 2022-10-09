import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
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
  private userId:number=0;
  id:string="";
  isEnrolled=false;

  @Input()course:Course={
    id:0,
    name:"",
    departament:"",
    description:""
  }

  private subscription!: Subscription;
  private subscriptonOnUser!: Subscription;

  constructor(private courseService:CourseService, private userService:UserService, private router:Router) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.subscriptonOnUser.unsubscribe();
  }

  ngOnInit(): void {

    this.subscriptonOnUser=this.userService.loggedUser.subscribe(response=>{
      this.userId=response;
     })

    this.subscription=this.userService.getEnrolledCoursesForUser(this.userId).subscribe(response=>{  
      response.forEach(e=>{
           if(this.course.id==e.id){
             this.isEnrolled=true;           
           }        
      })     
     });
  }

  public onClick(event:Event){

    this.router.navigate([`/courses/course-details/${this.course.id}/${this.isEnrolled}`]);
  }

}
