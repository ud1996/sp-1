package com.cognizant.vehiclereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.vehiclereservationsystem.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
}
