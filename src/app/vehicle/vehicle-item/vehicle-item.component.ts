import { Component, OnInit, Input, Output } from '@angular/core';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router } from '@angular/router';
import { VehicleService } from 'src/app/Services/vehicle.service';

@Component({
  selector: 'app-vehicle-item',
  templateUrl: './vehicle-item.component.html',
  styleUrls: ['./vehicle-item.component.css']
})
export class VehicleItemComponent implements OnInit {

  @Input() vehicle: Vehicle[];
  bookedVehicle:Vehicle;
  constructor(private authService: AuthenticationService, private router:Router,private vehicleService:VehicleService) { }

  ngOnInit() {

    console.log("inthe"+this.vehicle);
  }

  isLoggedIn(){
    this.authService.loggedIn;
  }

  isAdmin(){
   
    
    return this.authService.loggedIn && this.authService.isAdminUser();
  }


}
