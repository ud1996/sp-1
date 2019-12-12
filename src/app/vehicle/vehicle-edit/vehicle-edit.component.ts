import { Component, OnInit, Input } from '@angular/core';
import { Vehicle } from 'src/app/Model/vehicle.model';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VehicleService } from 'src/app/Services/vehicle.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {map,switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit {

 vehicleId:any;
 updatedVehicle:Vehicle;
 vehicle:Vehicle;
 editForm:FormGroup;
 formSubmitted: boolean = false;
  constructor(private vehicleService: VehicleService,private route:ActivatedRoute,private authService: AuthenticationService, private router:Router) { }

  ngOnInit() {

    // this.route.params.subscribe((params:Params)=>{
    //   this.vehicleId = params['veId'];
    //   console.log(this.vehicleId)
    // });

    // this.vehicleService.getVehicle(this.vehicleId).subscribe((v:Vehicle)=>{
    //       this.vehicle = v;
    //       console.log("EditVehicle"+v.imageUrl);
          
    // })
    this.route.params.pipe(
      map(params=> params['veId']),
      switchMap(ve=>this.vehicleService.getVehicle(ve))
    )
    .subscribe((v:Vehicle)=>{
      console.log(v);
      
      this.vehicle = v;
      console.log("EditVehicle"+v.imageUrl);
      this.editForm = new FormGroup({
        'imageUrl':new FormControl(null,Validators.required),
        'veName': new FormControl(null, Validators.required),
        'price': new FormControl(null, [Validators.min(0)]),
        'veType': new FormControl(null, Validators.required),
        'veInsuranceExpiryDate':new FormControl(null,Validators.required),
        'velastServiceDate':new FormControl(null,Validators.required),
        'veServiceDueDate':new FormControl(null,Validators.required),
        'veAvailability':new FormControl(null)
      });
      
     if(this.vehicle){
       console.log(this.vehicle);
       console.log("in patch");
       console.log(this.vehicle.veServiceDueDate);
       console.log(this.vehicle.velastServiceDate);
       console.log(this.vehicle.veInsuranceExpiryDate);
       console.log(this.vehicle.veAvailability);
     
       
       this.editForm.patchValue({
         veName: this.vehicle.veName,
         imageUrl:this.vehicle.imageUrl,
         price:this.vehicle.price,
         veType:this.vehicle.veType,
         veInsuranceExpiryDate:this.vehicle.veInsuranceExpiryDate,
         velastServiceDate:this.vehicle.velastServiceDate,
         veServiceDueDate:this.vehicle.veServiceDueDate,
         veAvailability:this.vehicle.veAvailability,
  
       });
     }
     else{
       console.log("not in patch");
      
     }
  
  
      
})
    console.log(this.vehicle);
    

      
  }

  onEditForm(){
    this.formSubmitted = true;
    let veId = this.vehicle.veId;
    let veName = this.editForm.get('veName').value;    
    let imageUrl = this.editForm.get('imageUrl').value;
    let price = this.editForm.get('price').value;
    let veType = this.editForm.get('veType').value;
    let veInsuranceExpiryDate=this.editForm.get('veInsuranceExpiryDate').value;
    let velastServiceDate=this.editForm.get('velastServiceDate').value;
    let veServiceDueDate=this.editForm.get('veServiceDueDate').value;

     this.updatedVehicle = {veId:veId,price:price,veType:veType,veInsuranceExpiryDate:veInsuranceExpiryDate,velastServiceDate:velastServiceDate,veName:veName,imageUrl:imageUrl,veServiceDueDate:veServiceDueDate,veAvailability:"avaialable"}

    this.vehicleService.updateVehicle(this.updatedVehicle).subscribe();
    this.editForm.reset();
  };
  



}

  

