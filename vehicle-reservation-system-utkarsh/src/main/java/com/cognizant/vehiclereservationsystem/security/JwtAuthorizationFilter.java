package com.cognizant.vehiclereservationsystem.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;



public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Start");
		String header=request.getHeader("Authorization");
		LOGGER.debug(header);
		
		if(header==null||!header.startsWith("Bearer ")) {
			LOGGER.info("Inside if");
			chain.doFilter(request, response);
			LOGGER.info("Inside if2");
			return;
		}
		UsernamePasswordAuthenticationToken authentication=getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		LOGGER.info("End");
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		if(token!=null) {
			Jws<Claims> jws;
			try {
				jws=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));
				String user=jws.getBody().getSubject();
				LOGGER.debug(""+jws);
				LOGGER.debug(""+jws.getBody());
				LOGGER.debug(""+jws.getBody().getSubject());
				LOGGER.debug(user);
				if(user!=null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			}catch(JwtException ex) {
				return null;
			}
			return null;
		}
		return null;
	}

}
