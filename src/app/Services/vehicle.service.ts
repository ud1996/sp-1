import { Injectable } from "@angular/core";
import { Subject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Vehicle } from '../Model/vehicle.model';

@Injectable({
    providedIn:'root'
})

export class VehicleService{
    filter = new Subject();
    token:string;

    constructor(private httpClient:HttpClient,private authenticationService:AuthenticationService){}

    getVehicles():Observable<any>{
        this.token = this.authenticationService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log("in getVehicles");
        
        return this.httpClient.get<any>("http://localhost:8000/vehicle/",{headers});

    }

    getVehicle(id:number):Observable<any>{
        this.token = this.authenticationService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);

        return this.httpClient.get<any>(`http://localhost:8000/vehicle/${id}`,{headers});
    }

    addVehicle(vehicle:Vehicle):Observable<any>{
        this.token = this.authenticationService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);

        return this.httpClient.post<void>(`http://localhost:8000/vehicle/${vehicle}`,{headers});
    }

    updateVehicle(vehicle:Vehicle):Observable<any>{
        this.token = this.authenticationService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log(vehicle);
        return this.httpClient.put<void>(`http://localhost:8000/vehicle/${vehicle}`,{headers});
    }

    deleteVehicle(vehicle:Vehicle):Observable<any>{
        this.token = this.authenticationService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
       
        
        return this.httpClient.delete<void>(`http://localhost:8000/vehicle/${vehicle}`,{headers});
    }


}