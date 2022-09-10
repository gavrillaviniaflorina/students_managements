import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.sass']
})
export class UserComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }
//@ts-ignore
@Input() user:User={
  id:Math.round( Math.random()*1000),
  name:"",
  email:"",
  password:"",
}

private subscription!: Subscription;

ngOnDestroy(): void {
  this.subscription.unsubscribe();
}
  ngOnInit(): void {

    this.subscription=this.userService.getUsers().subscribe(response=>{
      
    });
  }

  public onClick(event:Event){
    this.router.navigate([`user/user-details/${this.user.id}`]);
  }
}
