import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
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
      this.coursesChanged.next(response);
    })
   }

   getCourses():Observable<Course[]>{
    return this.http.get<Course[]>(this.api).pipe(
      tap((response:Course[])=>{
        this.coursesChanged.next(response);
      })
    ).pipe(tap(console.log),catchError(this.handleError));
   }

   findCourseById(id:number):Observable<Course>{
    return this.http.get<Course>(this.api+`/findCourseById/${id}`).pipe(tap(console.log),catchError(this.handleError));
   }

   addCourse(course:Course):Observable<Course>{
    this.coursesChanged.next([...this.coursesChanged.value,course]);
    return this.http.post<Course>(this.api+"/addCourse",course).pipe(tap(console.log),catchError(this.handleError));
   }

   updateCourse(course:Course, id:number):Observable<Course>{

    this.coursesChanged.next([...this.coursesChanged.value.filter(e=>e.id!=id), course]);

    return this.http.put<Course>(this.api+`/updateCourse`, course).pipe(tap(console.log),catchError(this.handleError));
   }

   private handleError(error:HttpErrorResponse):Observable<never>{
    let errorMessage:string;
  
    if(error.error instanceof ErrorEvent){
      errorMessage=`A client error ocurred -${error.error.message}`;
    }else{
  
      if(error.error.reason){
        errorMessage=`${error.error.reason} - Error code ${error.status}`;
      }else{
        errorMessage=error.error.message;
      }
    }
  
    return throwError(errorMessage);
  
  }
}
