import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Course } from 'src/app/models/course';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-courses',
  templateUrl: './user-courses.component.html',
  styleUrls: ['./user-courses.component.sass']
})
export class UserCoursesComponent implements OnInit , OnDestroy {
public courses:Course[]=[];

  private subscription!: Subscription;
  private userSubscribe!: Subscription;
  private courseSubscribe!: Subscription;

  constructor(private userService:UserService, private authentificationService:AuthentificationService) { }
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
   this.userSubscribe.unsubscribe();
   this.courseSubscribe.unsubscribe();
  }

  ngOnInit(): void {
     this.subscription= this.userService.enroledCourses.subscribe(response=>{
      this.courses=[...response];
    })

    this.userSubscribe=this.authentificationService.user.subscribe(userResponse=>{
      this.courseSubscribe=this.userService.getEnrolledCoursesForUser(userResponse.value).subscribe(courseResponse=>{
        this.userService.enroledCourses.next(courseResponse);
      })
    })
  }
}
