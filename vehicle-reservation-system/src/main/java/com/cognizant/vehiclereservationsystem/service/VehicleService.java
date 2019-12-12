package com.cognizant.vehiclereservationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.vehiclereservationsystem.model.Vehicle;
import com.cognizant.vehiclereservationsystem.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository  vehicleRepository;
	
	@Transactional
	public List<Vehicle> getVehicles(){
		return vehicleRepository.findAll();
	}
	
	@Transactional
	public Vehicle  getVehicleByName(String vehicleName){
		return vehicleRepository.findByVeName(vehicleName);
	}
	@Transactional
	public Vehicle getVehicleById(long vehicleId) {
		return vehicleRepository.findById(vehicleId).get();
	}
	@Transactional
	public void updateVehicleById(long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		vehicleRepository.save(vehicle);
	}
	
	@Transactional
	public boolean updateVehicle(Vehicle updatedVehicle) {
		try {
		Vehicle vehicle = getVehicleById(updatedVehicle.getVeId());
		vehicle.setVeAvailability(updatedVehicle.getVeAvailability());
		vehicle.setImageUrl(updatedVehicle.getImageUrl());
		vehicle.setVeInsuranceExpiryDate(updatedVehicle.getVeInsuranceExpiryDate());
		vehicle.setVelastServiceDate(updatedVehicle.getVelastServiceDate());
		vehicle.setVeName(updatedVehicle.getVeName());
		vehicle.setPrice(updatedVehicle.getPrice());
		vehicle.setVeServiceDueDate(updatedVehicle.getVeServiceDueDate());
		vehicle.setVeType(updatedVehicle.getVeType());
		vehicleRepository.save(vehicle);
		return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	public void deleteVehicle(long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		vehicleRepository.delete(vehicle);
	}
	
	@Transactional
	public void  addVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
}
