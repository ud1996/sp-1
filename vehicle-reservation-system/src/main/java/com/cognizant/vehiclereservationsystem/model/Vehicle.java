package com.cognizant.vehiclereservationsystem.model;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	@Id
	@Column(name = "ve_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long veId;

	@Column(name = "ve_name")
	private String veName;

	@Column(name = "ve_type")
	private String veType;

	@Column(name = "ve_no")
	private String veNumber;

	@Column(name = "ve_insurance_expiry_date")
	private LocalDate veInsuranceExpiryDate;

	@Column(name = "ve_last_service_date")
	private LocalDate velastServiceDate;

	@Column(name = "ve_service_due_date")
	private LocalDate veServiceDueDate;

	@Column(name = "ve_availability")
	private String veAvailability;

	@Column(name = "ve_price")
	private double price;

	@Column(name = "ve_image_url")
	private String imageUrl;;

	@OneToMany(mappedBy = "vehicle")
	private Set<Booking> booking;

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(long veId, String veName, String veType, String veNumber, LocalDate veInsuranceExpiryDate,
			LocalDate velastServiceDate, LocalDate veServiceDueDate, String veAvailability, double price, String imageUrl,
			Set<Booking> booking) {
		super();
		this.veId = veId;
		this.veName = veName;
		this.veType = veType;
		this.veNumber = veNumber;
		this.veInsuranceExpiryDate = veInsuranceExpiryDate;
		this.velastServiceDate = velastServiceDate;
		this.veServiceDueDate = veServiceDueDate;
		this.veAvailability = veAvailability;
		this.price = price;
		this.imageUrl = imageUrl;
		this.booking = booking;
	}
	
	

	public Vehicle(long veId, String veName, String veType, String veNumber, LocalDate veInsuranceExpiryDate,
			LocalDate velastServiceDate, LocalDate veServiceDueDate, String veAvailability, double price, String imageUrl) {
		super();
		this.veId = veId;
		this.veName = veName;
		this.veType = veType;
		this.veNumber = veNumber;
		this.veInsuranceExpiryDate = veInsuranceExpiryDate;
		this.velastServiceDate = velastServiceDate;
		this.veServiceDueDate = veServiceDueDate;
		this.veAvailability = veAvailability;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}

	public long getVeId() {
		return veId;
	}

	public void setVeId(long veId) {
		this.veId = veId;
	}

	public String getVeName() {
		return veName;
	}

	public void setVeName(String veName) {
		this.veName = veName;
	}

	public String getVeType() {
		return veType;
	}

	public void setVeType(String veType) {
		this.veType = veType;
	}

	public String getVeNumber() {
		return veNumber;
	}

	public void setVeNumber(String veNumber) {
		this.veNumber = veNumber;
	}

	public LocalDate getVeInsuranceExpiryDate() {
		return veInsuranceExpiryDate;
	}

	public void setVeInsuranceExpiryDate(LocalDate veInsuranceExpiryDate) {
		this.veInsuranceExpiryDate = veInsuranceExpiryDate;
	}

	public LocalDate getVelastServiceDate() {
		return velastServiceDate;
	}

	public void setVelastServiceDate(LocalDate velastServiceDate) {
		this.velastServiceDate = velastServiceDate;
	}

	public LocalDate getVeServiceDueDate() {
		return veServiceDueDate;
	}

	public void setVeServiceDueDate(LocalDate veServiceDueDate) {
		this.veServiceDueDate = veServiceDueDate;
	}

	public String getVeAvailability() {
		return veAvailability;
	}

	public void setVeAvailability(String veAvailability) {
		this.veAvailability = veAvailability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Vehicle [veId=" + veId + ", veName=" + veName + ", veType=" + veType + ", veNumber=" + veNumber
				+ ", veInsuranceExpiryDate=" + veInsuranceExpiryDate + ", velastServiceDate=" + velastServiceDate
				+ ", veServiceDueDate=" + veServiceDueDate + ", veAvailability=" + veAvailability + ", price=" + price
				+ "]";
	}

}
