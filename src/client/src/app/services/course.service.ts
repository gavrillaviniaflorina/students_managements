import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private api=environment.api+"/api/v1/courses";
  public coursesChanged=new BehaviorSubject<Course[]>([]);

  constructor(private http:HttpClient) {
    this.getCourses().subscribe((response)=>{
      
    })
   }

   getCourses():Observable<Course[]>{
    return this.http.get<Course[]>(this.api).pipe(
      tap((response:Course[])=>{
        this.coursesChanged.next(response)
      })
    )
   }

   findCourseById(id:number):Observable<Course>{
    return this.http.get<Course>(this.api+`/findCourseById/${id}`);
   }

}
