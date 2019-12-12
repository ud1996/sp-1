package com.cognizant.vehiclereservationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.vehiclereservationsystem.model.User;
import com.cognizant.vehiclereservationsystem.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getPending();
	}
	
	
	
	@GetMapping("/pending")
	public List<User> getPending(){
		return userService.getPending();
	}
	
	@GetMapping("/approveUser/{id}")
	public void approveUser(@PathVariable("id") long id){
		LOGGER.info("approveUser");
		 userService.approveUser(id);
	}
	
	@DeleteMapping("/declineUser/{id}")
	public void declineUser(@PathVariable("id") long id){
		LOGGER.info("declineUser");
		userService.declineUser(id);
	}
}
