import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.sass']
})
export class AllBooksComponent implements OnInit {
  public books:Book[]=[];
  constructor(private bookService:BookService,private router:Router ) { }

  ngOnInit(): void {
    this.bookService.booksChanged.subscribe(response=>{
      this.books=[...response];
    })
  }

}
