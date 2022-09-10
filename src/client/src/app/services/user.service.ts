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
      this.usersChanged.next(response);
    })
    
    this.getEnrolledCoursesForUser(4).subscribe(response=>{
      this.enroledCourses.next(response);
    })

    this.getBookedBooksForUser(4).subscribe(response=>{
      this.bookedBooks.next(response);
    })

  }

  getEnrolledCoursesForUser(id:number):Observable<Course[]>{
    return this.http.get<Course[]>(this.api+`/getCoursesOfUser/${id}`).pipe(tap((response:Course[])=>{
    },console.log),catchError(this.handleError)); 
  }

  getBookedBooksForUser(id:number):Observable<Book[]>{
    return this.http.get<Book[]>(this.api+`/getBooksOfUser/${id}`).pipe(tap((response:Book[])=>{
     
    },console.log),catchError(this.handleError)); 
  }

  getUsers():Observable<User[]>{
    return this.http.get<User[]>(this.api).pipe(
     
    )
  }

  addCourseForUser(userId:number, course:Course):Observable<Course>{

    this.enroledCourses.next([...this.enroledCourses.value, course]);
    return this.http.post<Course>(this.api+`/addCourseForUser/${userId}/${course.id}`,undefined).pipe(tap(console.log),catchError(this.handleError));
  }

  removeCourseForUser(userId:number, course:Course):Observable<Course>{
    this.enroledCourses.next([...this.enroledCourses.value.filter(e=>e.id!=course.id)])
    return this.http.delete<Course>(this.api+ `/deleteCourseForUser/${userId}/${course.id}`).pipe(tap(console.log),catchError(this.handleError));
  }

  addBookForUser(userId:number, book:Book):Observable<Book>{
    this.bookedBooks.next([...this.bookedBooks.value, book]);
    return this.http.post<Book>(this.api+`/addBookForUser/${userId}/${book.id}`,undefined).pipe(tap(console.log),catchError(this.handleError));
  }

  removeBookForUser(userId:number, book:Book):Observable<Book>{
    this.bookedBooks.next([...this.bookedBooks.value.filter(e=>e.id!=book.id)]);
    return this.http.delete<Book>(this.api+ `/deleteBookForUser/${userId}/${book.id}`).pipe(tap(console.log),catchError(this.handleError));
  }

  deleteUser( id:number):Observable<Course>{

    this.usersChanged.next([...this.usersChanged.value.filter(e=>e.id!=id)]);

    return this.http.delete<Course>(this.api+`/deleteUser/${id}`).pipe(tap(console.log),catchError(this.handleError));
   }

  getPdf(){
    return this.http.get(this.api+`/downloadCoursePDF`).pipe(tap(console.log),catchError(this.handleError));;
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




  