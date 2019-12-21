package com.cognizant.vehiclereservationsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="coupon")
public class Coupon {
	@Id
	@Column(name="cp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long couponId;
	
	@Column(name="cp_name")
	private String code;
	
	@Column(name="cp_value")
	private int value;
	
	@OneToMany(mappedBy="coupon",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	Set<Transaction> transaction;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(long couponId, String code, int value) {
		super();
		this.couponId = couponId;
		this.code = code;
		this.value = value;
		
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	
	
	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", code=" + code + ", value=" + value + ", transaction=" + transaction
				+ "]";
	}
	
	
}
