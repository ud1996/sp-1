import { Component, OnInit } from '@angular/core';
import { VehicleService } from 'src/app/Services/vehicle.service';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router } from '@angular/router';
import { Vehicle } from 'src/app/Model/vehicle.model';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  vehicles:Vehicle[];
  tempVehicle:Vehicle[];
  constructor(private vehicleService:VehicleService,private authService:AuthenticationService,private router:Router) { }

  ngOnInit() {
    this.vehicleService.getVehicles().subscribe((vehicle:Vehicle[])=>{
      console.log("vehicle lisrt=");
      
      this.vehicles = [...vehicle];
      this.tempVehicle = [...vehicle];
      console.log(this.vehicles);
      
    })
    console.log("1"+this.vehicles);
    this.vehicleService.filter.subscribe((obj:{veName:string})=>{
      if(obj.veName !== ''){
        const result = this.tempVehicle.filter(filterVehicle=>filterVehicle.veName.toLowerCase().includes(obj.veName.toLowerCase()));
        this.vehicles = result ? result:[];
      }
      else{
        this.vehicles = [...this.tempVehicle];
      }
    })
  }
 


}
