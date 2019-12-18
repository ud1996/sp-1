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
import { UserService } from 'src/app/Services/user.service';
import { Booking } from 'src/app/Model/booking.model';




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
  user:User = null;
  d:any;
  showTotal:boolean=false;
  confirm:boolean = false;
  email: string;
  showWalletError: boolean = false;
  showButton:boolean = false;
  amount:any;
  hasInput:boolean = false;
  walletAmount:any;
  status: string;
  constructor(private userService:UserService, private authService:AuthenticationService,private bookingService:BookingService,private  route:ActivatedRoute, private vehicleService:VehicleService,private datePipe: DatePipe,private rout:Router) {

    
  }

  ngOnInit() {
   
    
  
    
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

    this.email = this.authService.userAuthenticated.email;
    console.log(this.email);
    
    this.userService.getUser(this.email).subscribe((us:User)=>{
      this.user = us;
      this.walletAmount = this.user.wallet.amount;
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
     
      console.log(this.bookingForm.get('bookingFrom').value)
      this.startDate = this.bookingForm.get('bookingFrom').value;
      this.days = this.bookingForm.get('bookingUpto').value;
      this.totalAmount = this.v.price * this.days;
      this.setTotalAmount(this.totalAmount);
      console.log(this.user.wallet.amount);
      console.log(this.walletAmount);
      
     if(this.walletAmount >= this.totalAmount){
        this.bookingService.confirmBooking(this.user.email,this.vehicleId,this.startDate,this.days).subscribe((book:Booking)=>{
          this.status = book.status;
          console.log(this.status);
        
          
        });
      //  this.rout.navigate(['/booking']);
     }
    else{
        this.showWalletError = true;
        this.showButton = true;
    }
     /// 
  }

  onConfirm(){
    this.confirm = true;
  }

  onInput(){
    this.hasInput = true;
    this.showTotal = true;
    
    this.totalAmount = this.v.price * this.bookingForm.get('bookingUpto').value;
   
    if(this.walletAmount >= this.totalAmount){
      this.showButton = false;
    }
    else
      this.showButton = true;
  
  }

  redirect(){
    this.rout.navigate(['/wallet'])
  }

  addMoney(){

    this.userService.updateWalletBalance(this.email,this.amount).subscribe();
    this.walletAmount += this.amount;
    this.showButton = false;
  }


}
