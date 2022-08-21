import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
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

  constructor(private http:HttpClient) { }

  addCourseForUser(userId:number, courseId:number):Observable<Course>{

    return this.http.post<Course>(this.api+`/addCourseForUser/${userId}/${courseId}`,undefined).pipe(tap(console.log),catchError(this.handleError));
  }

  getEnrolledCoursesForUser(id:number):Observable<Course[]>{
    return this.http.get<Course[]>(this.api+`/getCoursesOfUser/${id}`).pipe(tap((response:Course[])=>{
      this.enroledCourses.next(response);
    },console.log),catchError(this.handleError)); 
  }

  removeCourseForUser(userId:number, courseId:number):Observable<Course>{
    return this.http.delete<Course>(this.api+ `/deleteCourseForUser/${userId}/${courseId}`).pipe(tap(console.log),catchError(this.handleError));
  }

  private handleError(error:HttpErrorResponse):Observable<never>{
    console.log(error);
    let errorMessage:string;
  
    if(error.error instanceof ErrorEvent){
      errorMessage=`A client error ocurred -${error.error.message}`;
    }else{
  
      if(error.error.reason){
        errorMessage=`${error.error.reason} - Error code ${error.status}`;
      }else{
        errorMessage=` An error ocurred -Error code ${error.status}`
      }
    }
  
    return throwError(errorMessage);
  
  }
}




  