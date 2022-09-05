import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.sass']
})
export class UserComponent implements OnInit {

  constructor(private userService:UserService) { }
//@ts-ignore
@Input() user:User={
  id:0,
  name:"",
  email:"",
  password:"",
}

  ngOnInit(): void {

    this.userService.getUsers().subscribe(response=>{
      
    });
  }

}
