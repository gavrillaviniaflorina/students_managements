import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
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
  isEnrolled:boolean=false;

  @Input() course:Course={
    id:0,
    name:"",
    departament: ""
  }
  
  constructor(private courseService:CourseService, private userService:UserService, private route:ActivatedRoute, private router:Router, private notificationService:NotificationService) { }
 
  ngOnInit(): void {

    this.route.params.subscribe(params=>{
      this.id=params['id'];
      this.isEnrolled= params['isEnrolled']=="true";
      this.courseService.findCourseById(+this.id).subscribe(response=>{
        this.course=response;
      })
    })

  }

  public joinCourse(){
      this.userService.addCourseForUser(this.userId,+this.id).subscribe( response=>{
        this.isEnrolled=true;
        this.notificationService.onSuccess("Success")
      }         
      )
  }

  public leaveCourse(){
    this.userService.removeCourseForUser(this.userId,+this.id).subscribe(response=>{
      this.isEnrolled=false;
      this.notificationService.onSuccess("You leaved this course");
    })
  }
}
