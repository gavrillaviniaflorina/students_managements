import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-new-book',
  templateUrl: './new-book.component.html',
  styleUrls: ['./new-book.component.sass']
})
export class NewBookComponent implements OnInit {

  constructor(private bookService:BookService, private router:Router, private notificationService:NotificationService) { }
  //@ts-ignore
  bookForm:FormGroup;
  private subscription!:Subscription;
  book:Book={
    id:0,
    book_name:"",
    created_at:""
  };

  ngOnInit(): void {
    this.initForm();  
  }

  private initForm():void{

    this.bookForm=new FormGroup({

      'name':new FormControl("",Validators.required),
      'created':new FormControl("",Validators.required)
    })
  }

  public onClick(event:Event){
    
    if(this.bookForm.valid==true){

      this.book.book_name=this.bookForm.value['name'];
      this.book.created_at=this.bookForm.value['created'];

      this.subscription=this.bookService.addBook(this.book).subscribe(response=>{  
         
        this.notificationService.onSuccess("The book was created");
        this.router.navigate(['/books']);
      },
      (err)=>{
        this.notificationService.onError(err);;
      }
      );
    }
    else{
      this.validare();
    }
    
  }

  public validare(){

    for(let e in this.bookForm.value){

      if(this.bookForm.value[e]==""){

        this.notificationService.onError(e+ " is required");
      }
    }

  
  }

  public OnCancel(event:Event){

    this.router.navigate(['/books']);
  }

}



