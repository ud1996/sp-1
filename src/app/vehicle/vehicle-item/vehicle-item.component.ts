import { Component, OnInit, Input } from '@angular/core';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vehicle-item',
  templateUrl: './vehicle-item.component.html',
  styleUrls: ['./vehicle-item.component.css']
})
export class VehicleItemComponent implements OnInit {

  @Input() vehicle: Vehicle[];

  constructor(private authService: AuthenticationService, private router:Router) { }

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
