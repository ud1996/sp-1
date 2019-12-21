package com.cognizant.vehiclereservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.vehiclereservationsystem.model.Vehicle;
import com.cognizant.vehiclereservationsystem.service.VehicleService;


@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping
	public List<Vehicle> getAll(){
		return vehicleService.getVehicles();
	}
	
	@GetMapping("/{vehicleId}")
	public Vehicle get(@PathVariable long vehicleId) {
		return vehicleService.getVehicleById(vehicleId);
	}
	
	
	
	@PutMapping
	public boolean update(@RequestBody Vehicle vehicle) {
		return vehicleService.updateVehicle(vehicle);
	}
	
	@PostMapping
	public void add(@RequestBody Vehicle vehicle) {
		 vehicleService.addVehicle(vehicle);
	}
	
	@DeleteMapping("/{vehicleId}")
	public void delete(@PathVariable long vehicleId) {
		vehicleService.deleteVehicle(vehicleId);
	}
}
