import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.sass']
})
export class BookComponent implements OnInit,OnDestroy {
  userId:number=2;
  id:string="";
  isBooked=false;

  @Input() book:Book={
    id:Math.round( Math.random()*1000),
    book_name:"",
    created_at:""
  }

  private subscription!: Subscription;

  constructor(private bookService:BookService,private userService:UserService, private router:Router) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.userService.getBookedBooksForUser(this.userId).subscribe(response=>{  
      response.forEach(e=>{
           if(this.book.id==e.id){
             this.isBooked=true;           
           }        
      })  
    
     });

     
  }

  public onClick(event:Event){

    this.router.navigate(['books/book-details/'+this.book.id+'/'+this.isBooked]);
  }


}
