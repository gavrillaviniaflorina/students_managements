import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.sass']
})
export class AllBooksComponent implements OnInit, OnDestroy {
  public books:Book[]=[];
  private subscription!: Subscription;

  constructor(
    private bookService:BookService,
    private router:Router)
    { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.bookService.booksChanged.subscribe(response=>{
      this.books=[...response];
      console.log(response);
    })
  }
}
