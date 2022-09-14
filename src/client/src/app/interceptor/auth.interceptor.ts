import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthentificationService } from '../services/authentification.service';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(private authentificationService:AuthentificationService) {}

  intercept(
    request: HttpRequest<any>, 
    handler: HttpHandler): Observable<HttpEvent<any>> {
    if(request.url.includes(`${environment.api}/login`)){
      return handler.handle(request);
    }
    // if(request.url.includes(`${environment.api}/students/addUser`)){
    //   return handler.handle(request);
    // }

    console.log("aici");
    this.authentificationService.loadToken();
    const token=this.authentificationService.getToken();
    const httpRequest=request.clone({
      setHeaders:{Authorization: `Bearer ${token}`}
    });
    return handler.handle(httpRequest);
  }
}
