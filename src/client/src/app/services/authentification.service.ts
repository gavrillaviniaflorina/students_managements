import { HttpClient } from '@angular/common/http';
import { Injectable, ɵsetAllowDuplicateNgModuleIdsForTest } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserCredentials } from '../models/login';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private api=environment.api+"/api/v1/students";
  //@ts-ignore
  private token: ?string;

  constructor(private http:HttpClient){}

  loginUser(credentials:UserCredentials):Observable<any>{
    return this.http.post<User>('http://localhost:9191/login', credentials,{observe:'response'})

  }

  loadToken():void{
    this.token=localStorage.getItem("jwtToken");
  }

  saveToken(token:string){
    localStorage.setItem("jwtToken",token);
  }

  getToken(){
    return this.token;
  }

  addUserToLocalCache(user:any){}
}
