import { Component, OnInit, Input } from '@angular/core';
import { Booking } from 'src/app/Model/booking.model';

@Component({
  selector: 'app-booking-info',
  templateUrl: './booking-info.component.html',
  styleUrls: ['./booking-info.component.css']
})
export class BookingInfoComponent implements OnInit {

  @Input() booking:Booking[];
  amount:number;
  
  constructor() { }

  ngOnInit() {
  }

}
