import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { UserService } from 'src/app/Services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  
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
    console.log(this.authService.userAuthenticated.firstName);
    
    return this.authService.userAuthenticated;
  }
  onSignOut(){
    this.authService.logout();
    this.router.navigate([this.authService.redirectUrl]);
  }

}
