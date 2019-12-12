package com.cognizant.vehiclereservationsystem.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.vehiclereservationsystem.Exception.UserAlreadyExistsException;
import com.cognizant.vehiclereservationsystem.model.Booking;
import com.cognizant.vehiclereservationsystem.model.User;
import com.cognizant.vehiclereservationsystem.service.AppUserDetailService;
import com.cognizant.vehiclereservationsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	AppUserDetailService appUserDetailService;
	
	@Autowired
	UserService userService;
	@PostMapping("/signup")
	public void SignUp(@RequestBody @Valid User user) throws UserAlreadyExistsException {
		
		LOGGER.info("signup");
		appUserDetailService.signUp(user);
	}
	
	@GetMapping
	public List<User>getPendingUser(){
		return userService.getPending();
	}
	
	@GetMapping("/getUser")
	public User getUser(long id){
		return userService.getUser(id);
	}
	
	@GetMapping("/booking/{email}")
	public Set<Booking> getBooking(@PathVariable String mail){
		return userService.findBookingByUser(mail);
	}
	
	
	
}
