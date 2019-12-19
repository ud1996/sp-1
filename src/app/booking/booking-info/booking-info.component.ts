import { Component, OnInit, Input } from '@angular/core';
import { Booking } from 'src/app/Model/booking.model';
import { BookingService } from 'src/app/Services/bookingService.service';
import { AuthenticationService } from 'src/app/Services/authentication.service';

@Component({
  selector: 'app-booking-info',
  templateUrl: './booking-info.component.html',
  styleUrls: ['./booking-info.component.css']
})
export class BookingInfoComponent implements OnInit {

  bookings:Booking[];
  amount:number;
  sortedBook:Booking[];
  constructor(private bookingService:BookingService,private authenticationService:AuthenticationService) { }

  ngOnInit() {

    
    this.bookingService.getBookingList(this.authenticationService.userAuthenticated.email).subscribe((book:Booking[])=>{
          
      this.bookings = [...book];
      console.log(book);
      Array.from(book)
      this.sortedBook = book.sort((obj1, obj2) => {
        if (obj1.bookingId < obj2.bookingId) {
            return 1;
        }
    
        if (obj1.bookingId > obj2.bookingId) {
            return -1;
        }
    
        return 0;
    });
    })
    
    
  }

onDelete(bookingId:number){
  this.bookingService.deleteBooking(bookingId).subscribe(()=>{
    this.ngOnInit();
  });
 
}

}
