import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
private api=environment.api+"/api/v1/students";
public usersChanged=new BehaviorSubject<User[]>([]);
public enroledCourses= new BehaviorSubject<Course[]>([]);

  constructor(private http:HttpClient) { 
  }

  addCourseForUser(userId:number, courseId:number):Observable<Course>{
    return this.http.post<Course>(this.api+`/addCourseForUser/${userId}/${courseId}`,undefined);
  }

  getEnrolledCoursesForUser(id:number):Observable<Course[]>{
    return this.http.get<Course[]>(this.api+`/getCoursesOfUser/${id}`).pipe(tap((response:Course[])=>{
      this.enroledCourses.next(response);
    })); 
  }
}
