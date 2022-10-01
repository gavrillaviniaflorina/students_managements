import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, ɵsetAllowDuplicateNgModuleIdsForTest } from '@angular/core';
import { catchError, Observable, throwError, tap} from 'rxjs';
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
    return this.http.post<User>(environment.api+'/login', credentials,{observe:'response'}).pipe(tap(console.log),catchError(this.handleError))
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
