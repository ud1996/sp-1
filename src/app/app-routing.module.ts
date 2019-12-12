import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { ApproveUserComponent } from './admin/approve-user/approve-user.component';
import { VehicleItemComponent } from './vehicle/vehicle-item/vehicle-item.component';
import { VehicleEditComponent } from './vehicle/vehicle-edit/vehicle-edit.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
import { ConfirmBookingComponent } from './booking/confirm-booking/confirm-booking.component';


const routes: Routes = [
  {path:'',component:VehicleListComponent},
  {path:'signup',component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'userapprove', component:ApproveUserComponent},
  {path:'edit/:veId',component:VehicleEditComponent},
  {path:'confirm/:veId',component:ConfirmBookingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
