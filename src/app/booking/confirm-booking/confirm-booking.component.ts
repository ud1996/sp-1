import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { BookingService } from 'src/app/Services/bookingService.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { VehicleService } from 'src/app/Services/vehicle.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/Model/user.model';
import { DatePipe, getLocaleDayNames } from '@angular/common';




@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.css']
})
export class ConfirmBookingComponent implements OnInit {
  isData:boolean=false;
  
  vehicleId:number;
  name:string;
  imageUrl:string;
  v:Vehicle;
  startDate:Date;
  days:any;
  bookingForm:FormGroup;
  user:User;
  d:any;
  constructor(private authService:AuthenticationService,private bookingService:BookingService,private  route:ActivatedRoute, private vehicleService:VehicleService,private datePipe: DatePipe) {

    
  }

  ngOnInit() {
    console.log("rerere");
    
    this.route.params.subscribe((params:Params)=>{
      this.vehicleId = params['veId'];
    });

    console.log(this.vehicleId);

    this.vehicleService.getVehicle(this.vehicleId).subscribe((ve:Vehicle)=>{
      
      this.v= ve;
      this.imageUrl=this.v.imageUrl;
      this.name=this.v.veName;
      console.log(this.v);
      
    })

    this.bookingForm = new FormGroup({
      bookingFrom :  new FormControl(null,Validators.required),
      bookingUpto: new FormControl(null,Validators.required)
    })
    
    
  }

  confirmBooking(){
    console.log("kk");
      this.user = this.authService.userAuthenticated;
      console.log(this.bookingForm.get('bookingFrom').value)
      this.startDate = this.bookingForm.get('bookingFrom').value;
      
      this.d = this.datePipe.transform(this.startDate,'dd-MM-yyyy');
      console.log(this.d);
      console.log(this.d.type);
     
      this.days = this.bookingForm.get('bookingUpto').value;
      this.bookingService.confirmBooking(this.user.email,this.vehicleId,this.d,this.days).subscribe();
  }

}
