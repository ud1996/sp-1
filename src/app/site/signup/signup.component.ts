
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Model/user.model';
import { UserService } from 'src/app/Services/user.service';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm:FormGroup;
  formSubmitted:boolean=false;
  user:User;
  asAdmin:boolean=false;
  roles=['User','Admin'];
  role:string="User";
  userAlreadyExists:boolean=false;
  usernameExists: boolean;
  constructor(private userService:UserService,private authService:AuthenticationService,private router:Router) { }
  displayVendor:boolean=false;
  ngOnInit() {
    
    this.signupForm=new FormGroup({
      'uname': new FormControl(null, [Validators.required,Validators.pattern("[^ @]*@[^ @]*"),Validators.maxLength(320)]),
      'fname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'lname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'pswd':new FormControl(null,Validators.required),
      'cpswd':new FormControl(null,[Validators.required,this.confirmPassword.bind(this)]),
      'gender':new FormControl(null,Validators.required),
      'contact':new FormControl(null,[Validators.required,Validators.minLength(10),Validators.maxLength(10)]),
      'age':new FormControl(null,[Validators.required,Validators.min(18),Validators.max(120)]),
      'vendorId':new FormControl(0),
      'role':new FormControl('User')
    });
   /*  if(this.signupForm.get('role').value=="admin"){
      this.displayVendor=true;
    } */
  }

  confirmPassword(formControl:FormControl){
    if(this.signupForm){
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signupForm.get('pswd').value){
        return { 'nomatch' : true };
      }
    }
    return null;
  }

  onSignup(){
    console.log("insignup");
    
    this.formSubmitted = true;
    let email = this.signupForm.get('email').value;
    
    
    let firstName = this.signupForm.get('firstName').value;
   
    let lastName = this.signupForm.get('lastName').value;
    let password = this.signupForm.get('password').value;
    let vendorId = this.signupForm.get('vendorId').value;
    console.log(this.signupForm.get('vendorId').value);
    let gender = this.signupForm.get('gender').value;
    console.log(this.signupForm.get('gender').value);
    console.log(this.signupForm.get('contactNumber').value);
   
   
    console.log("1233444");
    let age = this.signupForm.get('age').value;
    let contactNumber = this.signupForm.get('contactNumber').value;
    console.log("ddddnup2");
    this.user = {email:email,firstName:firstName,lastName:lastName,password:password,gender:"male",vendorId:vendorId,age:age,contactNumber:contactNumber,approved:false};
    console.log(this.user);
    
    this.userService.authentcate(this.user).subscribe((data)=>{
      console.log(data);
      
    },
    (error)=>{
      if(error['error']['message']==='User Already Exist'){
        this.usernameExists=true;
        
      }    
      this.usernameExists=true;
    });
    this.signupForm.reset();
    this.authService.redirectUrl = "/login";
        this.router.navigate([this.authService.redirectUrl]);
  }

  checkValue(){

    console.log(this.signupForm.get('genre'));
    this.role = this.signupForm.controls['genre'].value;
    if(this.role === "User")
      this.asAdmin = false;
    else
      this.asAdmin = true;
     
     console.log(this.asAdmin);
  }


onSignUp(){
  this.formSubmitted=true;
  let email=this.signupForm.get('uname').value;
  let firstname=this.signupForm.get('fname').value;
  let lastname=this.signupForm.get('lname').value;
  let password=this.signupForm.get('pswd').value;
  let age=this.signupForm.get('age').value;
  let contact=this.signupForm.get('contact').value;
  let gender=this.signupForm.get('gender').value;
  let role = this.signupForm.get('role').value;
  console.log(role);
  let vendorId;
  if(role==='User')
    vendorId = 0;
  else
    vendorId = this.signupForm.get('vendorId').value;
  this.user = {email:email,firstName:firstname,lastName:lastname,password:password,age:age,gender:gender,contactNumber:contact,approved:null,vendorId:vendorId};
  this.userAlreadyExists=false;
  this.userService.authentcate(this.user).subscribe((data)=>{
      console.log(data);
    },
    (error)=>{
      if(error['error']['message']==='User Already Exist'){
        this.userAlreadyExists=true;
      }        
    }
    );
    this.signupForm.reset();
}


}