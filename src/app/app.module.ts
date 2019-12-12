import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './site/header/header.component';
import { LoginComponent } from './site/login/login.component';
import { SignupComponent } from './site/signup/signup.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { ApproveUserComponent } from './admin/approve-user/approve-user.component';
import { VehicleItemComponent } from './vehicle/vehicle-item/vehicle-item.component';
import { VehicleEditComponent } from './vehicle/vehicle-edit/vehicle-edit.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
import { SearchComponent } from './search/search.component';
import { BookingListComponent } from './booking/booking-list/booking-list.component';
import { BookingInfoComponent } from './booking/booking-info/booking-info.component';
import { ConfirmBookingComponent } from './booking/confirm-booking/confirm-booking.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    SignupComponent,
    ApproveUserComponent,
    VehicleItemComponent,
    VehicleEditComponent,
    VehicleListComponent,
    SearchComponent,
    BookingListComponent,
    BookingInfoComponent,
    ConfirmBookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
