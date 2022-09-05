import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-user-dropdown',
  templateUrl: './user-dropdown.component.html',
  styleUrls: ['./user-dropdown.component.sass']
})
export class UserDropdownComponent implements OnInit {

  userId:number=4;
  constructor( private router:Router) { }

  ngOnInit(): void {
    
  }

  public myCourses(event:Event){

    this.router.navigate([`my-courses/${this.userId}`]);
  }

  public myBooks(event:Event){

    this.router.navigate([`my-books/${this.userId}`]);
  }

}
