import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-books',
  templateUrl: './user-books.component.html',
  styleUrls: ['./user-books.component.sass']
})
export class UserBooksComponent implements OnInit, OnDestroy {

  public books:Book[]=[];
  private subscription!:Subscription;
  private userSubscribe!: Subscription;
  private bookSubscribe!: Subscription;
  

  constructor(
    private userService:UserService, 
    private authentificationService:AuthentificationService) 
    { }
  
  ngOnDestroy(): void {
   this.subscription.unsubscribe();
   this.userSubscribe.unsubscribe();
   this.bookSubscribe.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.bookedBooks.subscribe(response=>{
      this.books=[...response];
    })

    this.userSubscribe=this.authentificationService.user.subscribe(userResponse=>{
      this.bookSubscribe=this.userService.getBookedBooksForUser(userResponse.value).subscribe(bookResponse=>{
        this.userService.bookedBooks.next(bookResponse);
      });
     })
  }
}
