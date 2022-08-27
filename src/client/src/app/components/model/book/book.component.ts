import { Component, Input, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.sass']
})
export class BookComponent implements OnInit {
  userId:number=4;
  id:string="";
  isBooked=false;

  @Input() book:Book={
    id:0,
    bookName:"",
    createdAt:""
  }
  constructor(private bookService:BookService,private userService:UserService) { }

  ngOnInit(): void {
    this.userService.getBookedBooksForUser(this.userId).subscribe(response=>{  
      response.forEach(e=>{
           if(this.book.id==e.id){
             this.isBooked=true;           
           }        
      })  
    
     });
  }

}
