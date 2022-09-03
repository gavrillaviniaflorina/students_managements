import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-new-course',
  templateUrl: './new-course.component.html',
  styleUrls: ['./new-course.component.sass']
})
export class NewCourseComponent implements OnInit {

  constructor(private courseService:CourseService, private router:Router, private notificationService:NotificationService) { }
  //@ts-ignore
  courseForm:FormGroup;
  
  course:Course={
    id:0,
    name:"",
    departament:""
  };

  ngOnInit(): void {
    this.initForm();
    console.log("aici");
  }

  private initForm():void{

    this.courseForm=new FormGroup({

      'name':new FormControl("",Validators.required),
      'departament':new FormControl("",Validators.required)
    })
  }

  public onClick(event:Event){
  
    if(this.courseForm.valid==true){

      this.course.name=this.courseForm.value['name'];
      this.course.departament=this.courseForm.value['departament'];

      this.courseService.addCourse(this.course).subscribe(response=>{       
        this.notificationService.onSuccess("The course was created");
        this.router.navigate(['/courses']);
      });
    }
    else{
      this.validare();
    }
    
  }

  public validare(){

    for(let e in this.courseForm.value){

      if(this.courseForm.value[e]==""){

        this.notificationService.onError(e+ " is required");
      }
    }
  }

  public OnCancel(event:Event){

    this.router.navigate(['/courses']);
  }

}
