import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { ApproveUserComponent } from './admin/approve-user/approve-user.component';
import { VehicleEditComponent } from './vehicle/vehicle-edit/vehicle-edit.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
import { ConfirmBookingComponent } from './booking/confirm-booking/confirm-booking.component';
import { AddVehicleComponent } from './Admin/add-vehicle/add-vehicle.component';
import { BookingListComponent } from './booking/booking-list/booking-list.component';
import { WalletComponent } from './wallet/wallet.component';
import { TransactionsComponent } from './transactions/transactions.component';


const routes: Routes = [
  {path:'',component:VehicleListComponent},
  {path:'signup',component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'userapprove', component:ApproveUserComponent},
  {path:'edit/:veId',component:VehicleEditComponent},
  {path:'confirm/:veId',component:ConfirmBookingComponent},
  {path:'addVehicle',component:AddVehicleComponent},
  {path:'booking',component:BookingListComponent},
  {path:'wallet',component:WalletComponent},
  {path:'transactions',component:TransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
