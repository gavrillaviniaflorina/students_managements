import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserCredentials } from 'src/app/models/login';
import { NotificationService } from 'src/app/services/notification.service';
import {AuthentificationService} from 'src/app/services/authentification.service';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  constructor(private fromBuilder:FormBuilder, private notificationService:NotificationService, private authentificationService:AuthentificationService, private router:Router ) { }
  //@ts-ignore
  loginForm:FormGroup;
  ngOnInit(): void {
    
    this.initForm();
  }

  login:UserCredentials={
    username:"",
    password:""
  }

  private initForm(){
    this.loginForm=new FormGroup({
      'email':new FormControl("", Validators.required),
      'password':new FormControl("",Validators.required)
    })
  }

  handleLogin(event:Event){
   
  this.login.username=this.loginForm.value['email'];
  this.login.password=this.loginForm.value['password'];
  console.log(this.login);
    this.userLogin(this.login);
  }
 
  private validare(){
    for(let e in this.loginForm.value){
      if(this.loginForm.value[e]==""){
        this.notificationService.onError(e+" is required");
      }
    }
  }

  userLogin(credentials:UserCredentials){
    this.authentificationService.loginUser(credentials).subscribe((response:HttpResponse<any>)=>{
      const token=response.headers.get('Authorization');
      this.authentificationService.saveToken(token!);
      this.router.navigate(['/books']);
    })
  }
  
}







