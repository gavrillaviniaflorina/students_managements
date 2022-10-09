import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-dropdown',
  templateUrl: './user-dropdown.component.html',
  styleUrls: ['./user-dropdown.component.sass']
})
export class UserDropdownComponent implements OnInit, OnDestroy {

  userId:number=0;
  private subscriptionOnUser!:Subscription; 

  constructor( private router:Router, private userService:UserService) { }
  
  ngOnInit(): void {
     this.subscriptionOnUser=this.userService.loggedUser.subscribe(response=>{
      this.userId=response;
    });
  }

  ngOnDestroy(): void {
    this.subscriptionOnUser.unsubscribe();
  }

  public myCourses(event:Event){

    this.router.navigate([`my-courses/${this.userId}`]);
  }

  public myBooks(event:Event){

    this.router.navigate([`my-books/${this.userId}`]);
  }

}
