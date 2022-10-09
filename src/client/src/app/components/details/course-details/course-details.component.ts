import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
import { Course } from 'src/app/models/course';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { CourseService } from 'src/app/services/course.service';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.sass']
})
export class CourseDetailsComponent implements OnInit, OnDestroy {
  userId:number=0;
  id:string="";
  isEnrolled:boolean=false;

  private subscriptionOnCourseChanged!:Subscription;
  private subscription!:Subscription;
  private subscriptionOnUser!:Subscription;

  @Input() course:Course={
    id:0,
    name:"",
    departament: "",
    description:""
  }

  
  constructor(
    private courseService:CourseService, 
    private userService:UserService,
    private route:ActivatedRoute,
    private router:Router, 
    private notificationService:NotificationService) 
    { }
 

    ngOnInit(): void {
      this.subscriptionOnUser=this.userService.loggedUser.subscribe(resp=>{
        this.userId=resp;
      })
      this.subscription=this.route.params.subscribe(params=>{
        this.id=params['id'];
        this.isEnrolled= params['isEnrolled']=="true";
        this. subscriptionOnCourseChanged=this.courseService.coursesChanged.subscribe(response=>{
          for(let i=0;i<response.length;i++){
            if(response[i].id===+this.id){
              this.course=response[i];
            }
          }
        })
      })
    }

  ngOnDestroy(): void {
   this.subscriptionOnCourseChanged.unsubscribe();
   this.subscription.unsubscribe();
   this.subscriptionOnUser.unsubscribe();
  }

  public joinCourse(){
    this.course.id=+this.id;
      this.userService.addCourseForUser(this.userId,this.course).subscribe( response=>{
        this.isEnrolled=true;
        this.notificationService.onSuccess("Success")
      }         
      )
  }

  public leaveCourse(){
    this.course.id=+this.id;
    this.userService.removeCourseForUser(this.userId,this.course).subscribe(response=>{
      this.isEnrolled=false;
      this.notificationService.onSuccess("You leaved this course");
    })
  }

  public editCourse(event:Event){
    this.router.navigate([`courses/edit-course/${this.course.id}`]);
  }

  public deleteCourse(event:Event){
    this.courseService.deleteCourse(+this.id).subscribe(response=>{       
      this.notificationService.onSuccess("The course was deleted");
      this.router.navigate(['/courses']);
    });
  }
}
