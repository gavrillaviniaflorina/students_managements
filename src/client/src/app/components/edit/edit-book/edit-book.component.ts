import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { NotificationService } from 'src/app/services/notification.service';


export let browserRefresh=false;

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.sass']
})
export class EditBookComponent implements OnInit, OnDestroy {

  constructor(private route:ActivatedRoute,private bookService:BookService, private router:Router, private notificationService:NotificationService) { 
   
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  //@ts-ignore
  bookForm:FormGroup;
  id:string='ceva';

  book:Book={
    id:0,
    book_name:"",
    created_at:"",
    description:""
  };

  private subscription!:Subscription;
  
  ngOnInit(): void {
   
    this.route.params.subscribe(params=>{
      this.id=params['id'];
      
      this.subscription=this.bookService.booksChanged.subscribe(response=>{
        for(let i=0;i<response.length;i++){
          if(response[i].id===+this.id){
            this.book=response[i];
          }
        }
      })
     
    })
    this.initForm(this.book);  
  }

  private initForm(book:Book):void{
    this.redirect();
    this.bookForm=new FormGroup({

      'book_name':new FormControl(book.book_name,Validators.required),
      'created_at':new FormControl(book.created_at,Validators.required),
      'description':new FormControl(book.description, Validators.required)
    })
  }

  public onEdit(event:Event){
  
    if(this.bookForm.valid==true){

      this.bookService.updateBook(this.bookForm.value, +this.id).subscribe(response=>{       
        this.notificationService.onSuccess("The book was updated");
        this.router.navigate(['/books']);
      });
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

  private redirect(){
  window.addEventListener("beforeunload", ()=>{
    this.router.navigate(['/books']);
  })
  }
}
