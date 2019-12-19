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
import { BookingInfoComponent } from './booking/booking-info/booking-info.component';
import { AuthGuardService } from './Services/auth-guard.service';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  {path:'',component:VehicleListComponent},
  {path:'signup',component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'userapprove', component:ApproveUserComponent,canActivate:[AuthGuardService]},
  {path:'edit/:veId',component:VehicleEditComponent,canActivate:[AuthGuardService]},
  {path:'confirm/:veId',component:ConfirmBookingComponent,canActivate:[AuthGuardService]},
  {path:'addVehicle',component:AddVehicleComponent,canActivate:[AuthGuardService]},
  {path:'booking',component:BookingInfoComponent,canActivate:[AuthGuardService]},
  {path:'wallet',component:WalletComponent,canActivate:[AuthGuardService]},
  {path:'transactions',component:TransactionsComponent,canActivate:[AuthGuardService]},
  {path:'profile',component:UserComponent,canActivate:[AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
