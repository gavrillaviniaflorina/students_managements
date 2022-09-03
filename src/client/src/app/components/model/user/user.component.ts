import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.sass']
})
export class UserComponent implements OnInit {

  constructor() { }
//@ts-ignore
@Input() user:User={
  id:0,
  name:"",
  email:"",
  password:"",
}

  ngOnInit(): void {
  }

}
