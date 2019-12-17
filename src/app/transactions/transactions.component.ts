import { Component, OnInit } from '@angular/core';
import { BookingService } from '../Services/bookingService.service';
import { UserService } from '../Services/user.service';
import { Transaction } from '../Model/transaction.model';
import { User } from '../Model/user.model';
import { AuthenticationService } from '../Services/authentication.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  user:User;
  email:string;
  transactions:Transaction[];
  
  constructor(private bookingService:BookingService, private userService:UserService,private authService:AuthenticationService) { }

  ngOnInit() {

    this.email = this.authService.userAuthenticated.email;
    this.userService.getUser(this.email).subscribe((us:User)=>{
      this.user = us;
      console.log(this.user);
    })
      
  }

}
