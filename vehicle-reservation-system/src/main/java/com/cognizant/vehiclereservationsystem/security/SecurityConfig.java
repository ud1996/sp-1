package com.cognizant.vehiclereservationsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.vehiclereservationsystem.service.AppUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	public SecurityConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Autowired
	private AppUserDetailService appUserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//auth.userDetailsService(inMemoryUserDetailsManager());
		//LOGGER.info("Configure auth start");
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
		//LOGGER.info("Configure auth end");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	
			//LOGGER.info("Configure HttpSecurity start");
					httpSecurity.cors();
					httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
					.antMatchers("/authenticate").permitAll().antMatchers("/user/**").permitAll()
					.antMatchers("/admin/**").permitAll()
					
					.antMatchers("/vehicle/**").permitAll()
					.anyRequest().authenticated()
					.and().addFilter(new JwtAuthorizationFilter(authenticationManager())); 
			 

		//LOGGER.info("Configure httpSecurity end");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/signup");
	} 
	
	
}
