import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.sass']
})
export class NavComponent implements OnInit {

  constructor(private router: Router) { }

  isSignedIn:boolean=false;

  user:User={
    id:0,
    name:"",
    email:"",
    password:""
  }
  ngOnInit( ): void {
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



}
