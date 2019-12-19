package com.cognizant.vehiclereservationsystem.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	public static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.debug("Start");
		String user = getUser(authHeader);
		LOGGER.debug(user);
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		LOGGER.debug("Startauth2");
		Map<String, String> auth = new HashMap<String, String>();
		auth.put("token", generateJwt(user));
		LOGGER.debug("endjwt");
		auth.put("role", role);
		LOGGER.debug("endrole");
		LOGGER.debug("End");
		return auth;
	}

	private String getUser(String authHeader) {
		byte[] auth = Base64.getDecoder().decode(authHeader.split(" ")[1]);
		String authStr = new String(auth);
		return authStr.split(":")[0];
	}

	private String generateJwt(String user) {
		LOGGER.debug("StartJwt");
		JwtBuilder builder = Jwts.builder();
		LOGGER.debug("Startjwt2");
		builder.setSubject(user);
		LOGGER.debug("Startjwt3");
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime() + 1200000));
		LOGGER.debug("Startjwt4");
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		LOGGER.debug("Startjwt5");
		String token = builder.compact();
		LOGGER.debug("Startjwt6");
		return token;
	}
}
