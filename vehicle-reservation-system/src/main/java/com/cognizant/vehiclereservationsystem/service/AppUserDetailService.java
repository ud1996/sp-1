package com.cognizant.vehiclereservationsystem.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.vehiclereservationsystem.Exception.UserAlreadyExistsException;
import com.cognizant.vehiclereservationsystem.model.Role;
import com.cognizant.vehiclereservationsystem.model.User;
import com.cognizant.vehiclereservationsystem.repository.RoleRepository;
import com.cognizant.vehiclereservationsystem.repository.UserRepository;
import com.cognizant.vehiclereservationsystem.security.AppUser;

@Service
public class AppUserDetailService implements UserDetailsService {

	Logger LOGGER = LoggerFactory.getLogger(AppUserDetailService.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	AppUser appUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		LOGGER.info("Load by userName start");

		User user = userRepository.findByEmail(username);
		LOGGER.debug("UserRepository:{}", userRepository);
		LOGGER.debug("User:{}", user);

		if (user == null)
			throw new UsernameNotFoundException("UserName not Found");
		else
			appUser = new AppUser(user);

		LOGGER.info("LoadUserByUserName end");
		return appUser;
	}

	public void signUp(User newUser) throws UserAlreadyExistsException {
		LOGGER.info("signUp user1");
		User user = userRepository.findByEmail(newUser.getEmail());
		Role role;
		if (user == null) {
			LOGGER.info("signUp user");
			if(newUser.getVendorId() == 0) {
				 role = roleRepository.findById((long) 2).get();
				
			}
			else {
			 role = roleRepository.findById((long) 1).get();
			}
			String password = newUser.getPassword();
			Set<Role> roleList = new HashSet<Role>();
			LOGGER.info("roleList user");
			roleList.add(role);
			newUser.setRoleList(roleList);
			newUser.setPassword(passwordEncoder().encode(password));
			userRepository.save(newUser);
			LOGGER.info("AppUserDetailService encoder");
		} else
			throw new UserAlreadyExistsException();
	}

	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		LOGGER.info("password encoder");
		return new BCryptPasswordEncoder();
	}

}
