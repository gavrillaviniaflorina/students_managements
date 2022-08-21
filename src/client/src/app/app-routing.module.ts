import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllCoursesComponent } from './components/all/all-courses/all-courses.component';
import { CourseDetailsComponent } from './components/details/course-details/course-details.component';
import { UserCoursesComponent } from './components/forUser/user-courses/user-courses.component';
import { NewCourseComponent } from './components/new/new-course/new-course.component';

const routes: Routes = [
  {path:'', redirectTo:'/courses',pathMatch:'full'},
  {path:'courses',component:AllCoursesComponent},
  {path:'courses/course-details/:id/:isEnrolled',component:CourseDetailsComponent},
  {path:'courses/new-course',component:NewCourseComponent},
  {path:'courses/my-courses/:userId', component:UserCoursesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
