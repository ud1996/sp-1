package com.cognizant.vehiclereservationsystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="User Already Exists")
public class UserAlreadyExistsException extends Exception {
	
	
}
