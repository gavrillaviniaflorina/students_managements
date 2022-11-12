import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
import { User } from 'src/app/models/user';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.sass']
})
export class NavComponent implements OnInit, OnDestroy {

  constructor(private router: Router, private authentificationService:AuthentificationService) { }
 
  isSignedIn:boolean=false;

  private subscriptionOnUser!:Subscription; 
   userId:number=0;

  ngOnInit( ): void {
    this.subscriptionOnUser=this.authentificationService.user.subscribe({
      next: (response) =>{
        if(response===null){
          this.isSignedIn=false;      
        }else{
          this.userId=response;
          this.isSignedIn=true;
        }      
      } 
    });
  }

  ngOnDestroy(): void {
    this.subscriptionOnUser.unsubscribe();
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
