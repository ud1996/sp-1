import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../Services/user.service';
import { User } from '../Model/user.model';
import { AuthenticationService } from '../Services/authentication.service';
import { Wallet } from '../Model/wallet.model';
import { BookingService } from '../Services/bookingService.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit {

  user:User = null;
  wallet:Wallet;
  email:string;
  totalAmount:any;
  amount:any;
  constructor(private userService:UserService ,private authService:AuthenticationService,private bookService:BookingService) { }

  ngOnInit() {
   
    this.totalAmount = this.bookService.totalAmount;
    this.email = this.authService.userAuthenticated.email;
    this.userService.getWallet(this.email).subscribe((wa:Wallet)=>{
      this.wallet = wa;
      console.log(this.wallet.amount);
    })
    console.log(this.wallet);
    this.email = this.authService.userAuthenticated.email;
    this.userService.getUser(this.email).subscribe((us:User)=>{
      this.user = us;
      console.log(this.user);
    })
  }
 
  addMoney(){

    this.userService.updateWalletBalance(this.email,this.amount).subscribe();
    this.userService.getUser(this.email).subscribe((us:User)=>{
      this.user = us;
      console.log(this.user);
    })
  }



}
