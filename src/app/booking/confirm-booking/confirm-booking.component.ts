import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { BookingService } from 'src/app/Services/bookingService.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { VehicleService } from 'src/app/Services/vehicle.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/Model/user.model';
import { DatePipe, getLocaleDayNames } from '@angular/common';
import { Route } from '@angular/compiler/src/core';




@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.css']
})
export class ConfirmBookingComponent implements OnInit {
  isData:boolean=false;
  totalAmount:number;
  vehicleId:number;
  name:string;
  imageUrl:string;
  v:Vehicle=null;
  startDate:Date;
  days:any;
  bookingForm:FormGroup;
  user:User;
  d:any;
  confirm:boolean = false;
  constructor(private authService:AuthenticationService,private bookingService:BookingService,private  route:ActivatedRoute, private vehicleService:VehicleService,private datePipe: DatePipe,private rout:Router) {

    
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

  setTotalAmount(totalAmount:any){
    this.bookingService.totalAmount = totalAmount;
  }

  confirmBooking(){
    console.log("kk");
      this.user = this.authService.userAuthenticated;
      console.log(this.bookingForm.get('bookingFrom').value)
      this.startDate = this.bookingForm.get('bookingFrom').value;
      this.days = this.bookingForm.get('bookingUpto').value;
      this.totalAmount = this.v.price * this.days;
      this.setTotalAmount(this.totalAmount);
      this.bookingService.confirmBooking(this.user.email,this.vehicleId,this.startDate,this.days).subscribe();
      this.rout.navigate(['/wallet']);
  }

  onConfirm(){
    this.confirm = true;
  }

}
