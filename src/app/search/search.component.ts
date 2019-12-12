import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../Services/vehicle.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private vehicleSerivce:VehicleService) { }

  ngOnInit() {
  }

  onSearchText(event:any){
    this.vehicleSerivce.filter.next({veName:event.target.value});
  }

}
