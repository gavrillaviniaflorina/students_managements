import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewBookComponent } from './components/add/new-book/new-book.component';
import { NewCourseComponent } from './components/add/new-course/new-course.component';
import { AllBooksComponent } from './components/all/all-books/all-books.component';
import { AllCoursesComponent } from './components/all/all-courses/all-courses.component';
import { AllUsersComponent } from './components/all/all-users/all-users.component';
import { BookDetailsComponent } from './components/details/book-details/book-details.component';
import { CourseDetailsComponent } from './components/details/course-details/course-details.component';
import { UserCoursesComponent } from './components/forUser/user-courses/user-courses.component';


const routes: Routes = [
  {path:'', redirectTo:'/courses',pathMatch:'full'},
  {path:'courses',component:AllCoursesComponent},
  {path:'books',component:AllBooksComponent},
  {path:'users',component:AllUsersComponent},
  {path:'courses/course-details/:id/:isEnrolled',component:CourseDetailsComponent},
  {path:'books/book-details/:id/:isBooked',component:BookDetailsComponent},
  {path:'courses/my-courses/:userId', component:UserCoursesComponent},
  {path:'courses/new-course', component:NewCourseComponent},
  {path:'books/new-book', component:NewBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
