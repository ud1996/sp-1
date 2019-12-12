import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/Model/booking.model';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { BookingService } from 'src/app/Services/bookingService.service';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {

  bookings: Booking[];
  

  constructor(private authenticationService:AuthenticationService,private bookingService:BookingService) { }

  ngOnInit() {
      this.bookingService.getBookingList(this.authenticationService.userAuthenticated.email).subscribe((book:Booking[])=>{

        this.bookings = [...book];
        console.log(book);
        
      })
    
  }


}
