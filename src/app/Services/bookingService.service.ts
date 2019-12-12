import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn:'root'
})

export class BookingService{

    constructor(private authService:AuthenticationService,private httpClient:HttpClient){}

    getBookingList(email:any):Observable<any>{
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.authService.accessToken)

        return this.httpClient.get<any>(`http://localhost:8000/user/booking/${email}`, { headers })
    }

}