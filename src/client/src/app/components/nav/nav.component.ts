import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.sass']
})
export class NavComponent implements OnInit {

  constructor(private router: Router) { }

  isSignedIn:boolean=false;
  ngOnInit( ): void {
  }

  public goToCourses(): void {
   
    this.router.navigate(['/courses']);
  }

  public goToBooks(): void {
   
    this.router.navigate(['/books']);
  }

  public goToUsers(): void {
   
    this.router.navigate(['/users']);
  }



}
