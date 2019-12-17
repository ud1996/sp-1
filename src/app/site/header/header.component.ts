import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { UserService } from 'src/app/Services/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user:User=null;
  email:string;
  constructor(private authService:AuthenticationService, private userService:UserService,private router:Router) { }

  ngOnInit() {

    
    
   
  }

  isAuthenticated(){
    this.authService.authSource="customer";
    return this.authService.loggedIn;
  }
  isAdmin(){
    return this.authService.isAdminUser();
  }
  getUser(){
    console.log(this.authService.userAuthenticated.email);
    
    return this.authService.userAuthenticated.email;
  }
  onSignOut(){
    this.authService.logout();
    this.router.navigate([this.authService.redirectUrl]);
  }

}
