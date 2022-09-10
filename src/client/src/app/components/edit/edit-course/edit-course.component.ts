import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.sass']
})
export class EditCourseComponent implements OnInit, OnDestroy {

  constructor(private courseService:CourseService, private router:Router, private route:ActivatedRoute, private notificationService:NotificationService) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private subscription!:Subscription;
  //@ts-ignore
  courseForm:FormGroup;
  id:string='ceva'; 
  course:Course={
    id:Math.round( Math.random()*1000),
    name:"",
    departament:"",
    description:""
  };

  ngOnInit(): void {
   
    this.route.params.subscribe(params=>{
      this.id=params['id'];
      

      this.subscription=this.courseService.coursesChanged.subscribe(response=>{
        for(let i=0;i<response.length;i++){
          if(response[i].id===+this.id){
            this.course=response[i];
          }
        }
      })
    })
    this.initForm(this.course);
  }

  private initForm(course:Course):void{

    this.courseForm=new FormGroup({

      'name':new FormControl(course.name,Validators.required),
      'departament':new FormControl(course.departament,Validators.required),
      'description': new FormControl(course.description,Validators.required)
    })
  }

  public onEdit(event:Event){
  
    if(this.courseForm.valid==true){

    this.courseService.updateCourse(this.courseForm.value, +this.id).subscribe(response=>{
      this.notificationService.onSuccess("The course was updated");
      this.router.navigate(['/courses']);
    })
      
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
