package com.cognizant.vehiclereservationsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="wallet")
public class Wallet {
	@Id
	@Column(name="wa_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long walletId;
	
	@Column(name="wa_amount")
	double amount;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="wa_us_id")
	User user;

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wallet(long walletId, double amount) {
		super();
		this.walletId = walletId;
		this.amount = amount;
	}
	
	

	public Wallet(double amount, User user) {
		super();
		this.amount = amount;
		this.user = user;
	}

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", amount=" + amount + ", user=" + user + "]";
	}
	
	
}
