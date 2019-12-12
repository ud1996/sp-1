package com.cognizant.vehiclereservationsystem.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.vehiclereservationsystem.model.Booking;
import com.cognizant.vehiclereservationsystem.model.User;
import com.cognizant.vehiclereservationsystem.repository.UserRepository;

@Service
public class UserService {
	Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public List<User> getpendingUsers(){
		return userRepository.getPending();
	}
	
	@Transactional
	public User getUser(long id) {
		return userRepository.findById(id).get();
	}
	
	@Transactional
	public List<User> getPending(){
		
		return userRepository.getPending();
	}
	
	@Transactional
	public void approveUser(long id){
		User user  = userRepository.findById(id).get();
		user.setApproved(true);
		userRepository.save(user);
	}
	
	@Transactional
	public void declineUser(long id){
		User userdel  = userRepository.findById(id).get();
		LOGGER.debug("user",userdel.toString());
		LOGGER.info("user",userdel);
		userRepository.delete(userdel);
		//userRepository.declineUser(id);
	}
	
	@Transactional
	public Set<Booking> findBookingByUser(String email) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			return user.getBooking();
		}
		return null;
		
	}
	
	
}
