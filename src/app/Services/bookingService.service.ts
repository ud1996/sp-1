
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { DatePipe } from '@angular/common';

@Injectable({
        providedIn: 'root'
})
export class BookingService {
        credentials: string;
        accessToken: string = '';
        totalAmount:any;
        constructor(private httpClient: HttpClient, private authService: AuthenticationService) { }
        getBookingList(email: string) {
                let headers = new HttpHeaders();
                headers = headers.set('Authorization', 'Bearer ' + this.authService.accessToken);
                return this.httpClient.get(`http://localhost:8000/user/bookings/${email}`, { headers });
        }
        confirmBooking(email: string, vehicleId: number, startDate: Date, days: number) {
                console.log("llll");
                let headers = new HttpHeaders();
               
                headers = headers.set('Authorization', 'Bearer ' + this.authService.accessToken);
                console.log("222");
                console.log("http://localhost:8000/user/booking/" + email + "/" + vehicleId + "/" + startDate + "/" + days);


                return this.httpClient.post<any>(`http://localhost:8000/user/booking/${email}/${vehicleId}/${startDate}/${days}`, { headers });
        }
        deleteBooking(bookingId: number) {
                let headers = new HttpHeaders();
                headers = headers.set('Authorization', 'Bearer ' + this.authService.accessToken);
                return this.httpClient.delete<any>(`http://localhost:8000/user/booking/${bookingId}`, { headers });
        }
}

