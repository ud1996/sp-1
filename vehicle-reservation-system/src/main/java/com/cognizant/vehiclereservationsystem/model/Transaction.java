package com.cognizant.vehiclereservationsystem.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@Column(name="tr_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long transaction_id;
	
	@Column(name="tr_type")
	String type;
	
	@Column(name="tr_amount")
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="tr_us_id")
	@JsonIgnore
	User user;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="tr_cp_id")
	@JsonIgnore
	Coupon coupon;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="tr_bk_id")
	@JsonIgnore
	Booking booking;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Transaction(double amount,String type ) {
		super();
		this.type = type;
		this.amount = amount;
	}


	public Transaction(long transaction_id, String type, double amount) {
		super();
		this.transaction_id = transaction_id;
		this.type = type;
		this.amount = amount;
	}
	

	public Transaction( Booking booking,User user,String type, double amount,  Coupon coupon) {
		super();
		this.type = type;
		this.amount = amount;
		this.user = user;
		this.coupon = coupon;
		this.booking = booking;
	}

	

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}
	
	

	

	public User getUser() {
		return user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", type=" + type + ", amount=" + amount + "]";
	}
	
	
}
