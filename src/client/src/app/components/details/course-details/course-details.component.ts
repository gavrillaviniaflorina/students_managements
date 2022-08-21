import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.sass']
})
export class CourseDetailsComponent implements OnInit {
  userId:number=4;
  id:string="";
  isEnrolled=false;

  @Input() course:Course={
    id:0,
    name:"",
    departament: ""
  }
  constructor(private courseService:CourseService, private userService:UserService, private route:ActivatedRoute, private router:Router, private notificationService:NotificationService) { }
 
  ngOnInit(): void {

    this.userService.getEnrolledCoursesForUser(this.userId).subscribe(response=>{  
     
     
    response.forEach(e=>{

         if(this.course.id==e.id){


          console.log("aici");
           this.isEnrolled=true;
         }
         console.log(this.isEnrolled);
    })
 
   });

    this.route.params.subscribe(params=>{
      this.id=params['id'];
      this.courseService.findCourseById(+this.id).subscribe(response=>{
        this.course=response;
      })
    })
  }

  public JoinCourse(){
      this.userService.addCourseForUser(this.userId,+this.id).subscribe( response=>{
        this.notificationService.onSuccess("Success")
      }         
      )
  }

  public LeaveCourse(){
    this.userService.removeCourseForUser(this.userId,+this.id).subscribe(response=>{
      this.notificationService.onSuccess("You leaved this course");
    })
  }

  
  public warrning(){
    this.notificationService.onWarning("You are already enrolled to this course");
  }

  


  

}
