import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from '../models/book';
import { Course } from '../models/course';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
private api=environment.api+"/api/v1/students";
public usersChanged=new BehaviorSubject<User[]>([]);
public enroledCourses= new BehaviorSubject<Course[]>([]);
public bookedBooks= new BehaviorSubject<Book[]>([]);

  constructor(private http:HttpClient) { 
    this.getUsers().subscribe((response)=>{

    })
  }

  addCourseForUser(userId:number, courseId:number):Observable<Course>{

    return this.http.post<Course>(this.api+`/addCourseForUser/${userId}/${courseId}`,undefined).pipe(tap(console.log),catchError(this.handleError));
  }


  getEnrolledCoursesForUser(id:number):Observable<Course[]>{
    return this.http.get<Course[]>(this.api+`/getCoursesOfUser/${id}`).pipe(tap((response:Course[])=>{
      this.enroledCourses.next(response);
    },console.log),catchError(this.handleError)); 
  }

  getBookedBooksForUser(id:number):Observable<Book[]>{
    return this.http.get<Book[]>(this.api+`/getBooksOfUser/${id}`).pipe(tap((response:Book[])=>{
      this.bookedBooks.next(response);
    },console.log),catchError(this.handleError)); 
  }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>(this.api).pipe(
      tap((response:User[])=>{
        this.usersChanged.next(response);
      })
    )
  }

  removeCourseForUser(userId:number, courseId:number):Observable<Course>{
    return this.http.delete<Course>(this.api+ `/deleteCourseForUser/${userId}/${courseId}`).pipe(tap(console.log),catchError(this.handleError));
  }

  addBookForUser(userId:number, bookId:number):Observable<Book>{

    return this.http.post<Book>(this.api+`/addBookForUser/${userId}/${bookId}`,undefined).pipe(tap(console.log),catchError(this.handleError));
  }

  removeBookForUser(userId:number, bookId:number):Observable<Book>{
    return this.http.delete<Book>(this.api+ `/deleteBookForUser/${userId}/${bookId}`).pipe(tap(console.log),catchError(this.handleError));
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




  