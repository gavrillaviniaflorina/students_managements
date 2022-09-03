import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { UserServiceService } from '../../../services/user-service.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.sass']
})
export class AllUsersComponent implements OnInit {
  public users:User[]=[];
  constructor(private userService:UserServiceService) { }

  ngOnInit(): void {
    this.userService.usersChanged.subscribe(response=>{
      this.users=[...response]; 
      console.log(response); 
    })
  }

}
