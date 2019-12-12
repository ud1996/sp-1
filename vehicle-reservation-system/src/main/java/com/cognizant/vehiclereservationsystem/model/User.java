package com.cognizant.vehiclereservationsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="us_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	
	@Column(name="us_firstname")
	private String firstName;
	
	@Column(name="us_lastname")
	private String lastName;
	
	@Column(name="us_email")
	private String email;
	
	@Column(name="us_password")
	private String password;
	
	@Column(name="us_vendor_id")
	private long vendorId;
	
	@Column(name="us_age")
	private int age;
	
	@Column(name="us_gender")
	private String gender;
	
	@Column(name="us_contact_no")
	private long contactNumber;
	
	@Column(name="us_is_approved")
	private boolean isApproved;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="ur_us_id"),inverseJoinColumns=@JoinColumn(name="ur_ro_id"))
	Set<Role>roleList;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	Wallet wallet;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	Set<Transaction> transaction;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	Set<Booking> booking;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long userId, String firstName, String lastName, String email, String password, long vendorId, int age,
			String gender, long contactNumber, boolean isApproved) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.vendorId = vendorId;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.isApproved = isApproved;
	}
	
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	

	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}

	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", vendorId=" + vendorId + ", age=" + age + ", gender=" + gender
				+ ", contactNumber=" + contactNumber + ", isApproved=" + isApproved + "]";
	}

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	
	
}
