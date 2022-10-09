import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.sass']
})
export class NavComponent implements OnInit, OnDestroy {

  constructor(private router: Router, private userService:UserService) { }
 
  isSignedIn:boolean=false;

  private subscriptionOnUser!:Subscription; 
  private userId:number=0;
  private subscriptionOnLoggedUser!:Subscription;

  user:User={
    id:0,
    name:"",
    email:"",
    password:""
  }
  ngOnInit( ): void {
    this.subscriptionOnUser=this.userService.loggedUser.subscribe(response=>{
      this.userId=response;
    });

    this.subscriptionOnLoggedUser=this.userService.getUserFormId(this.userId).subscribe(response=>{
      this.user=response;
    })
  }

  ngOnDestroy(): void {
    this.subscriptionOnUser.unsubscribe();
    this.subscriptionOnLoggedUser.unsubscribe();
  }

  public goToCourses(): void {
   
    this.router.navigate(['/courses']);
  }

  public goToBooks(): void {
   
    this.router.navigate(['/books']);
  }

  public goToUsers(): void {
   
    this.router.navigate(['/users']);
  }

  public goToSignIn(): void{
    this.router.navigate(['login']);
  }
}
