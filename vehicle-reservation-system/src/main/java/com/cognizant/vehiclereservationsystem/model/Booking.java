package com.cognizant.vehiclereservationsystem.model;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="booking")
public class Booking {
	@Id
	@Column(name="bk_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bookingId;
	
	@Column(name="bk_start_date")
	private LocalDate startDate;
	
	@Column(name="bk_end_date")
	private LocalDate endDate;
	
	@Column(name="bk_booking_date")
	private LocalDate bookingDate;
	
	@Column(name="bk_status")
	private String status;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="bk_ve_id")
	@JsonIgnore
	private Vehicle vehicle;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="bk_us_id")
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="booking",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Set<Transaction> transaction;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(long bookingId, LocalDate startDate, LocalDate endDate, LocalDate bookingDate, String status) {
		super();
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	
	
	

	public Booking(User user,Vehicle vehicle,LocalDate startDate, LocalDate endDate, LocalDate bookingDate, String status ) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookingDate = bookingDate;
		this.status = status;
		this.vehicle = vehicle;
		this.user = user;
		
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", bookingDate=" + bookingDate + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
}
