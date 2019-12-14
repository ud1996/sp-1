package com.cognizant.vehiclereservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@SpringBootApplication
public class VehicleReservationSystemApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(VehicleReservationSystemApplication.class, args);
	}

	
}
