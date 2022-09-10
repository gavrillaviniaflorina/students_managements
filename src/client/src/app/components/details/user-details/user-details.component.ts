import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.sass']
})
export class UserDetailsComponent implements OnInit {
;
  id:string="";
 

  @Input() user:User={
    id:0,
    name:"",
    email: "",
    password:""
  
  }
  
  private subscription!: Subscription;
  constructor( private userService:UserService, private route:ActivatedRoute, private router:Router, private notificationService:NotificationService) { }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription=this.route.params.subscribe(params=>{
      this.id=params['id'];
      
    })
  }

 

public editBook(event:Event){
  this.router.navigate([`books/edit-book/${this.user.id}`]);
}

public deleteBook(event:Event){
  this.userService.deleteUser(+this.id).subscribe(response=>{       
    this.notificationService.onSuccess("The book was deleted");
    this.router.navigate(['/books']);
  });
}

}
