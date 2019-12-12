import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/Model/user.model';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User;
  error:string;
  
  
  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthenticationService,private userService:UserService) { }
  isLoginValid:boolean=true;
  ngOnInit() {
  }

  onSubmit(form:NgForm){
    const username = form.value.uname;
    const password = form.value.pass;
    console.log(username);
    console.log(password);
    
    
    this.authService.authenticate(username,password).subscribe(data=>{
        this.authService.accessToken = data['token'];
        this.authService.role = data['role'];
        console.log("role"+this.authService.role);
        
        if(this.authService.role !== 'ROLE_ADMIN'){
          console.log("isAdmin0");
          
          this.authService.isAdmin = false;
          this.authService.userAuthenticated = {email:username,password:password}
        }
        else{
          console.log("isAdmin1");
          this.authService.isAdmin = true;
          this.authService.userAuthenticated = {email:username,password:password};
        }

        this.authService.loggedIn = true;
        this.authService.redirectUrl = "/";
        this.router.navigate([this.authService.redirectUrl]);
    },
    (error)=>{
      if(error['status']===401){
        this.isLoginValid=false;
      }
    }
    )
    
    
  }
}
