import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.sass']
})
export class BookDetailsComponent implements OnInit {

  userId:number=2;
  id:string="";
  isBooked:boolean=false;

  @Input() book:Book={
    id:0,
    book_name:"",
    created_at: ""
  }
  
  constructor(private bookService:BookService, private userService:UserService, private route:ActivatedRoute, private router:Router, private notificationService:NotificationService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{
      this.id=params['id'];
      this.isBooked= params['isBooked']=="true";
      this.bookService.findBookById(+this.id).subscribe(response=>{
        this.book=response;
      })
    })
  }

  public takeBook(){
    this.userService.addBookForUser(this.userId,+this.id).subscribe( response=>{
      this.isBooked=true;
      this.notificationService.onSuccess("Success")
    }         
    )
}

public returnBook(){
  this.userService.removeBookForUser(this.userId,+this.id).subscribe(response=>{
    this.isBooked=false;
    this.notificationService.onSuccess("You returned this book");
  })
}

}
