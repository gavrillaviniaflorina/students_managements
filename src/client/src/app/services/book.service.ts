import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private api=environment.api+"/api/v1/books";
  public booksChanged=new BehaviorSubject<Book[]>([]);

  constructor(private http:HttpClient) { 
    this.getBooks().subscribe((response)=>{
      this.booksChanged.next(response);
    })
  }

  getBooks():Observable<Book[]>{
    return this.http.get<Book[]>(this.api).pipe(
      tap((response:Book[])=>{
        this.booksChanged.next(response);
      })
    ).pipe(catchError(this.handleError));
   }

   addBook(book:Book):Observable<Book>{
    this.booksChanged.next([...this.booksChanged.value,book]);
    return this.http.post<Book>(this.api+"/addBook",book).pipe(tap(console.log),catchError(this.handleError));
   }

   updateBook(book:Book, id:number):Observable<Book>{

    this.booksChanged.next([...this.booksChanged.value.filter(e=>e.id!=id), book]);

    return this.http.put<Book>(this.api+`/updateBook`, book).pipe(tap(console.log),catchError(this.handleError));
   }

   deleteBook(id:number):Observable<Book>{

    this.booksChanged.next([...this.booksChanged.value.filter(e=>e.id!=id)]);

    return this.http.delete<Book>(this.api+`/deleteBook/${id}`).pipe(tap(console.log),catchError(this.handleError));
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
