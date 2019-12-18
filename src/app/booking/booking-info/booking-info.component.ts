import { Component, OnInit, Input } from '@angular/core';
import { Booking } from 'src/app/Model/booking.model';
import { BookingService } from 'src/app/Services/bookingService.service';

@Component({
  selector: 'app-booking-info',
  templateUrl: './booking-info.component.html',
  styleUrls: ['./booking-info.component.css']
})
export class BookingInfoComponent implements OnInit {

  @Input() booking:Booking[];
  amount:number;
  
  constructor(private bookingService:BookingService) { }

  ngOnInit() {

    console.log(this.booking);
    
    
  }

onDelete(bookingId:number){
  this.bookingService.deleteBooking(bookingId).subscribe();
}

}
