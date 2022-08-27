import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap} from 'rxjs';
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

    })
  }

  getBooks():Observable<Book[]>{
    return this.http.get<Book[]>(this.api).pipe(
      tap((response:Book[])=>{
        this.booksChanged.next(response)
      })
    )
   }

   findCourseById(id:number):Observable<Book>{
    return this.http.get<Book>(this.api+`/findBookById/${id}`);
   }


}
