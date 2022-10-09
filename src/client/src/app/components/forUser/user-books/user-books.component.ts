import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-books',
  templateUrl: './user-books.component.html',
  styleUrls: ['./user-books.component.sass']
})
export class UserBooksComponent implements OnInit, OnDestroy {

  public books:Book[]=[];
  private subscription!:Subscription;

  constructor(
    private userService:UserService, 
    private router:Router) 
    { }
  
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.bookedBooks.subscribe(response=>{
      this.books=[...response];
    })
  }
}
