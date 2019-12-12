package com.cognizant.vehiclereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.vehiclereservationsystem.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
