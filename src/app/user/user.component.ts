import { Component, OnInit } from '@angular/core';
import { UserService } from '../Services/user.service';
import { User } from '../Model/user.model';
import { AuthenticationService } from '../Services/authentication.service';
import { Transaction } from '../Model/transaction.model';




@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  amount: number = 0;
  user: User = null;
  length: number = 0
  walletAmount: number = 0;
  transactions:Transaction[];
  constructor(private userService: UserService, private authService: AuthenticationService) { }

  ngOnInit() {

    this.userService.getUser(this.authService.userAuthenticated.email).subscribe((us: User) => {

      this.user = us;
      this.walletAmount = this.user.wallet.amount;
      if (this.user != null)
        this.length = 1;
      console.log(this.length);

      Array.from(this.user.transaction);
      this.transactions = this.user.transaction.sort((obj1,obj2)=>{
          if(obj1.transaction_id < obj2.transaction_id)
              return 1;

          else if(obj1.transaction_id > obj2.transaction_id)
          return -1;

          else
            return 0;
      })
      console.log(this.transactions);
      
    })

    
  }

  addMoney() {


    this.userService.updateWalletBalance(this.authService.userAuthenticated.email, this.amount).subscribe(() => {

      this.walletAmount = this.user.wallet.amount;
      this.ngOnInit();
      console.log(this.walletAmount);

    });



  }

}
