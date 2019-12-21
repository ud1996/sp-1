package com.cognizant.vehiclereservationsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.vehiclereservationsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query(value="select * from user where us_is_approved=0 and us_vendor_id = 0 ",nativeQuery=true)
	List<User> getPending();
	
	@Query(value="update user set us_is_approved = 1 where user.us_id = ?1",nativeQuery=true)
	void approveUser(long id);
	
	@Query(value="delete from user where user.userId = ?1",nativeQuery=true)
	void declineUser(long id);
	
	@Query(value="select * from user where us_is_approved=1 and us_vendor_id = 0 ",nativeQuery=true)
	List<User> getApproved();
	
	@Query(value="select * from user where us_is_approved=0 and us_vendor_id != 0 ",nativeQuery=true)
	List<User> getPendingAdmin();
	
	@Query(value="select * from user where us_is_approved=1 and us_vendor_id != 0 ",nativeQuery=true)
	List<User> getApprovedAdmin();
	
}
