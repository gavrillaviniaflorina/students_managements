import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../../models/user';


@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.sass']
})
export class AllUsersComponent implements OnInit, OnDestroy {
  public users:User[]=[];
  private subscription!: Subscription;
  
  constructor(private userService:UserService, private router:Router) { }
  
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.usersChanged.subscribe(response=>{
      this.users=[...response]; 
    })
  }

}
