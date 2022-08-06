import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable , tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private api=environment.api+"api/v1/users";
  public usersChanged=new BehaviorSubject<User[]>([]);

  constructor(private http:HttpClient) { 
    this.getUsers().subscribe((response)=>{})
  }

  getUsers():Observable<User[]>{

    return this.http.get<User[]>(this.api).pipe(
      tap((response:User[])=>{
        this.usersChanged.next(response);
      })
    )
  }
}
