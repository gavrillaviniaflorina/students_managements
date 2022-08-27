import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './components/model/user/user.component';
import { BookComponent } from './components/model/book/book.component';
import { CourseComponent } from './components/model/course/course.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { NavComponent } from './components/nav/nav.component';
import { UserDropdownComponent } from './components/user-dropdown/user-dropdown.component';
import { AllUsersComponent } from './components/all/all-users/all-users.component';
import { AllCoursesComponent } from './components/all/all-courses/all-courses.component';
import { CourseDetailsComponent } from './components/details/course-details/course-details.component';
import { BookDetailsComponent } from './components/details/book-details/book-details.component';
import { NewCourseComponent } from './components/add/new-course/new-course.component';
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { UserCoursesComponent } from './components/forUser/user-courses/user-courses.component';
import { AllBooksComponent } from './components/all/all-books/all-books.component';



@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    BookComponent,
    CourseComponent,
    HomeComponent,
    HeaderComponent,
    NavComponent,
    UserDropdownComponent,
    AllUsersComponent,
    AllCoursesComponent,
    CourseDetailsComponent,
    BookDetailsComponent,
    NewCourseComponent,
    UserCoursesComponent,
    AllBooksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
