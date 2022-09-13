import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Component } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './components/model/user/user.component';
import { BookComponent } from './components/model/book/book.component';
import { CourseComponent } from './components/model/course/course.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { NavComponent } from './components/nav/nav.component';
import { UserDropdownComponent } from './components/user-dropdown/user-dropdown.component';
import { AllCoursesComponent } from './components/all/all-courses/all-courses.component';
import { CourseDetailsComponent } from './components/details/course-details/course-details.component';
import { BookDetailsComponent } from './components/details/book-details/book-details.component';
import { NewCourseComponent } from './components/add/new-course/new-course.component';
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { UserCoursesComponent } from './components/forUser/user-courses/user-courses.component';
import { AllBooksComponent } from './components/all/all-books/all-books.component';
import { AllUsersComponent } from './components/all/all-users/all-users.component';
import { NewBookComponent } from './components/add/new-book/new-book.component';
import { EditCourseComponent } from './components/edit/edit-course/edit-course.component';
import { UserBooksComponent } from './components/forUser/user-books/user-books.component';
import { EditBookComponent } from './components/edit/edit-book/edit-book.component';
import { UserDetailsComponent } from './components/details/user-details/user-details.component';
import { LoginComponent } from './components/login/login.component';



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
    AllCoursesComponent,
    AllUsersComponent,
    CourseDetailsComponent,
    BookDetailsComponent,
    NewCourseComponent,
    UserCoursesComponent,
    AllBooksComponent,
    NewBookComponent,
    EditCourseComponent,
    UserBooksComponent,
    EditBookComponent,
    UserDetailsComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
