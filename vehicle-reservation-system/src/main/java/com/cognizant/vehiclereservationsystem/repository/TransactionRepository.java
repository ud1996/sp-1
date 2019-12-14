package com.cognizant.vehiclereservationsystem.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.vehiclereservationsystem.model.Booking;
import com.cognizant.vehiclereservationsystem.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
		
	public Set<Transaction> findByBooking(Booking booking);	
}
