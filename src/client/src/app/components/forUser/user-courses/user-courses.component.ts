import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Course } from 'src/app/models/course';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-courses',
  templateUrl: './user-courses.component.html',
  styleUrls: ['./user-courses.component.sass']
})
export class UserCoursesComponent implements OnInit , OnDestroy {
public courses:Course[]=[];

  private subscription!: Subscription;
  constructor(private userService:UserService, private router:Router) { }
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
  }

  ngOnInit(): void {
     this.subscription= this.userService.enroledCourses.subscribe(response=>{
      this.courses=[...response];
    })
  }
}
