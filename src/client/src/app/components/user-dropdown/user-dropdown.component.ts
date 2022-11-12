import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-dropdown',
  templateUrl: './user-dropdown.component.html',
  styleUrls: ['./user-dropdown.component.sass']
})
export class UserDropdownComponent implements OnInit, OnDestroy {

  @Input() userId:number=0;
  private subscriptionOnUser!:Subscription; 
  private subscriptionOnLoggedUser!:Subscription;

  user:User={
    id:0,
    name:"",
    email:"",
    password:""
  }
  constructor( private router:Router, private userService:UserService, private authentificationService:AuthentificationService) { }
  
  ngOnInit(): void {

     this.subscriptionOnLoggedUser=this.userService.getUserFormId(this.userId).subscribe({
      next :(response) => this.user=response,
    })
  }

  ngOnDestroy(): void {
   this.subscriptionOnLoggedUser.unsubscribe();
  }

  public myCourses(event:Event){

    this.router.navigate([`my-courses/${this.userId}`]);
  }

  public myBooks(event:Event){

    this.router.navigate([`my-books/${this.userId}`]);
  }

  public logout(event:Event){

    this.authentificationService.logout();
    this.authentificationService.user.next(null);
    
    this.router.navigate(["login"]);
  }
}
