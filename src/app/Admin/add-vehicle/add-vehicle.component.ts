import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { VehicleService } from 'src/app/Services/vehicle.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Vehicle } from 'src/app/Model/vehicle.model';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {

  constructor(private authService:AuthenticationService,private vehicleService:VehicleService) { 

    this.addVehicleForm = new FormGroup({
      'imageUrl':new FormControl(null,Validators.required),
      'veName': new FormControl(null, Validators.required),
      'price': new FormControl(null, [Validators.min(0)]),
      'veType': new FormControl(null, Validators.required),
      'veInsuranceExpiryDate':new FormControl(null,Validators.required),
      'velastServiceDate':new FormControl(null,Validators.required),
      'veServiceDueDate':new FormControl(null,Validators.required),
      'veAvailability':new FormControl(null)
      })
  
  }
  addVehicleForm:FormGroup;
  formSubmitted:boolean=false;
  vehicle:Vehicle;
  ngOnInit() {

   

  }

  onAddVehicle(){
    console.log("kkk");
    this.formSubmitted = true;
    let veName = this.addVehicleForm.get('veName').value;    
    let imageUrl = this.addVehicleForm.get('imageUrl').value;
    let price = this.addVehicleForm.get('price').value;
    let veType = this.addVehicleForm.get('veType').value;
    let veInsuranceExpiryDate=this.addVehicleForm.get('veInsuranceExpiryDate').value;
    let velastServiceDate=this.addVehicleForm.get('velastServiceDate').value;
    let veServiceDueDate=this.addVehicleForm.get('veServiceDueDate').value;
    this.vehicle = {veName:veName,veType:veType,veInsuranceExpiryDate:veInsuranceExpiryDate,velastServiceDate:velastServiceDate,veServiceDueDate:veServiceDueDate,veAvailability:"avaialable",price:price,imageUrl:imageUrl}

    this.vehicleService.addVehicle(this.vehicle).subscribe();
  }



}
