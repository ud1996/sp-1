package com.cognizant.vehiclereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.vehiclereservationsystem.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
		
	public Vehicle findByVeName(String vehicleName);
}
