import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { BookingService } from 'src/app/Services/bookingService.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { VehicleService } from 'src/app/Services/vehicle.service';

@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.css']
})
export class ConfirmBookingComponent implements OnInit {

  vehicleId:number;
  vehicle:Vehicle;
  constructor(private authService:AuthenticationService,private bookingService:BookingService,private  route:ActivatedRoute, private vehicleService:VehicleService) { }

  ngOnInit() {

    this.route.params.subscribe((params:Params)=>{
      this.vehicleId = params['vehicleId'];
    });

    this.vehicleService.getVehicle(this.vehicleId).subscribe((v:Vehicle)=>{
      this.vehicle = v;
      console.log(v+"insode getVehicle");
      
    })
  }



}
