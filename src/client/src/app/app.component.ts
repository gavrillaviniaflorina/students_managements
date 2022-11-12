import { Component, HostListener, OnDestroy } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthentificationService } from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent{
  title = 'client';

  @HostListener("window:beforeunload", ["$event"]) unloadHandler(event: Event) {
    console.log("Processing beforeunload...");
    this.refresh();
  }
  public constructor(private router: Router, private authentificationService: AuthentificationService){
    this.router.navigate(['/']);
  }

  private refresh(){
    this.authentificationService.logout();
    this.authentificationService.user.next(null);
  }

}
