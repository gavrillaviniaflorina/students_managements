import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '../../../models/user';
import { UserServiceService } from '../../../services/user-service.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.sass']
})
export class AllUsersComponent implements OnInit, OnDestroy {
  public users:User[]=[];
  private subscription!: Subscription;
  
  constructor(private userService:UserServiceService) { }
  
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.usersChanged.subscribe(response=>{
      this.users=[...response]; 
    })
  }

}
