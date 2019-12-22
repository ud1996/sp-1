package com.cognizant.vehiclereservationsystem.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.vehiclereservationsystem.model.Booking;
import com.cognizant.vehiclereservationsystem.model.Coupon;
import com.cognizant.vehiclereservationsystem.model.Transaction;
import com.cognizant.vehiclereservationsystem.model.User;
import com.cognizant.vehiclereservationsystem.model.Vehicle;
import com.cognizant.vehiclereservationsystem.model.Wallet;

import com.cognizant.vehiclereservationsystem.repository.BookingRepository;
import com.cognizant.vehiclereservationsystem.repository.CouponRepository;
import com.cognizant.vehiclereservationsystem.repository.TransactionRepository;
import com.cognizant.vehiclereservationsystem.repository.UserRepository;
import com.cognizant.vehiclereservationsystem.repository.VehicleRepository;
import com.cognizant.vehiclereservationsystem.repository.WalletRepository;

@Service
public class UserService {
	Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CouponRepository couponRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Transactional
	public List<User> getpendingUsers(){
		return userRepository.getPending();
	}
	
	@Transactional
	public List<User> getpendingAdmin(){
		return userRepository.getPendingAdmin();
	}
	
	@Transactional
	public List<User> getApprovedAdmin(){
		return userRepository.getApprovedAdmin();
	}
	
	@Transactional
	public List<User> getApprovedUser(){
		return userRepository.getApproved();
	}
	
	@Transactional
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Transactional
	public List<User> getPending(){
		
		return userRepository.getPending();
	}
	
	@Transactional
	public void approveUser(long id){
		User user  = userRepository.findById(id).get();
		user.setApproved(true);
		userRepository.save(user);
	}
	
	@Transactional
	public void declineUser(long id){
		User userdel  = userRepository.findById(id).get();
		LOGGER.debug("user",userdel.toString());
		LOGGER.info("user",userdel);
		userRepository.delete(userdel);
		//userRepository.declineUser(id);
	}
	
	@Transactional
	public Set<Booking> findBookingByUser(String email) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			return user.getBooking();
		}
		return null;
		
	}
	
	public Wallet addMoney(double amountTotal, String email, long bookingId, String type) {
		double amount = amountTotal;
		User user = getUser(email);
		Wallet wallet = user.getWallet();
		if (type.equals("debit")) {
			if (wallet != null) {
				amount = amountTotal - wallet.getAmount();
			} else {
				amount = amountTotal;
			}
		}

		Set<Transaction> transactionList = user.getTransaction();
		Booking booking = bookingRepository.findById(bookingId).get();
		if (transactionList.size() == 0) {
			Transaction transaction = new Transaction(booking, user, type, amount, null);
			transactionList.add(transaction);
			user.setTransaction(transactionList);
		}
		if (wallet != null) {
			if (type.equals("debit")) {
				wallet.setAmount(wallet.getAmount()-amount);
			} else if (type.equals("credit")) {
				wallet.setAmount(wallet.getAmount() + amount);
			}
			Transaction transaction = new Transaction(booking, user, type, amount, null);
			transactionList.add(transaction);

		} else if (wallet == null) {
			wallet = new Wallet(0, user);
		}
		user.setWallet(wallet);
		userRepository.save(user);
		return wallet;
	}

	
	

	@Transactional

	public Map<String, String> bookVehicle(String email, long vehicleId, LocalDate startDate, int numberOfDays,long couponId) {

		User user = getUser(email);
		Coupon coupon = couponRepository.findById(couponId).get();
		Map<String, String> response = new HashMap<String, String>();
		Set<Transaction> transactionList = user.getTransaction();
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();

		Set<Booking> bookingList = user.getBooking();
		for (Booking bookingCheck : bookingList) {
			if (bookingCheck.getStatus().equals("active")) {
				// Cannot Book as ride is Active
				response.put("status", "unsuccessfull");
				response.put("reason", "You already have booked a ride.");
				return response;
			}
		}
		Wallet wallet = user.getWallet();

		LocalDate currentDate = LocalDate.now();

		Booking booking = new Booking(user, vehicle, startDate, startDate.plusDays(numberOfDays), currentDate,
				"pending payment");

		bookingList.add(booking);

		Booking getBooking = bookingRepository.save(booking);

		double amount = (numberOfDays * (vehicle.getPrice()))-coupon.getValue();

		if (wallet == null || wallet.getAmount() < amount) {
			addMoney(amount, email, getBooking.getBookingId(), "debit");
		} else {
			wallet.setAmount(wallet.getAmount() - amount);
			Transaction transaction = new Transaction(booking, user, "debit", amount, coupon);
			transactionList.add(transaction);
			user.setWallet(wallet);
		}
		// wallet.setAmount(wallet.getAmount() - amount);
		// check if user has already booked a vehicle
		booking.setStatus("active");
		bookingRepository.save(booking);
		// userRepository.save(user);
		response.put("status", "successfull");

		return response;
	}


	public Map<String, String> cancelBooking(long bookingId) {

		Booking booking = bookingRepository.findById(bookingId).get();
		User user = userRepository.findById(booking.getUser().getUserId()).get();
		Map<String, String> response = new HashMap<String, String>();
		Set<Transaction> transactionListUser = user.getTransaction();

		LocalDate startDate = booking.getStartDate();
		int difference = startDate.compareTo(LocalDate.now());
		if (!booking.getStatus().equals("active")) {
			response.put("status", "unsuccessfull");
			response.put("reason", "Booking not active.");
			return response;
		}
		float deduction = 0;

		if (difference >= 2) {
			deduction = 0.1f;
		} else if (difference == 1) {
			deduction = 0.2f;
		} else if (difference == 0) {
			deduction = 0.3f;
		}
		// Transaction transaction =
		// transactionRepository.findByBookingAndType(booking,"debit");
		Set<Transaction> transactions = getTransactionByBooking(user.getEmail(), bookingId);
		System.out.println(transactions.size());
		double transactionAmount = 0;
		for (Transaction transaction2 : transactions) {
			if (transaction2.getType().equals("debit")) {
				transactionAmount = transaction2.getAmount();
				break;
			}
		}
		double refundAmount = transactionAmount * (1 - deduction);
		Math.ceil(refundAmount);
		addMoney(refundAmount, user.getEmail(), bookingId, "credit");

		booking.setStatus("cancelled");
		bookingRepository.save(booking);

		response.put("status", "booking cancelled successfully !");

		return response;
	}

	
	public Set<Transaction> getTransactions(String email) {
		User user = getUser(email);
		try {
			return user.getTransaction();
		} catch (Exception e) {
			return null;
		}
	}


	public Set<Booking> getBookingList(String email) {
		User user = getUser(email);
		if (user != null) {
			return user.getBooking();
		}
		return null;
	}


	public Set<Transaction> getTransactionByBooking(String email, long bookingId) {

		try {
			Booking booking = bookingRepository.findById(bookingId).get();
			if (booking != null) {
				return transactionRepository.findByBooking(booking);
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	@Transactional
	public Wallet getWalletByEmail(String email){
		User user = getUser(email);
		return user.getWallet();
	}
	
	@Transactional
	public void updateWalletBalance(String email,double amount) {
		System.out.println("jhjhg");
		Wallet wallet;
		User user = userRepository.findByEmail(email);
		if(user.getWallet() != null) {
			wallet = user.getWallet();
		}
		else {
			wallet = new Wallet(0,user);
		}
		wallet.setAmount(wallet.getAmount()+amount);
		user.setWallet(wallet);
		Transaction transaction = new Transaction(null, user, "credit", amount, null);
		Set<Transaction> transactionSet;
		transactionSet = user.getTransaction();
		transactionSet.add(transaction);
		user.setTransaction(transactionSet);
		for(Transaction tra: transactionSet) {
			System.out.println(tra);
		}
		walletRepository.save(wallet);
		transactionRepository.save(transaction);
		userRepository.save(user);
		
		
	}
	
	public String checkBookingStatus(String email) {
		
		User user = userRepository.findByEmail(email);
		Set<Booking> bookingList = user.getBooking();
		for(Booking book : bookingList) {
			if(book.getStatus() == "active") {
				return "sorry you already have booked a vehicle";
			}
		}
		return "you can proceed";
	}
	
	@Transactional
	public List<Coupon>getCoupon(){
		return couponRepository.findAll();
	}
	public boolean deleteNotification(long notificationId) {
		try {
			Notification notification = notificationRepository.findById(notificationId).get();
			notificationRepository.delete(notification);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		public boolean clearNotifications(String email) {
		try {
			User user = userRepository.findByEmail(email);
			List<Notification> notifications = notificationRepository.findByUser(user);
			for (Notification notification : notifications) {
				notificationRepository.delete(notification);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void notifyForInsuranceAndService(String email) {
		List<Vehicle> vehicleList = vehicleRepository.findAll();

		for (Vehicle vehicle : vehicleList) {
			boolean notify = false;
			boolean notifyService = false;

			long intervalDays = ChronoUnit.DAYS.between(LocalDate.now(), vehicle.getVeInsuranceExpiryDate());
			long intervalDaysService = ChronoUnit.DAYS.between(LocalDate.now(), vehicle.getVeServiceDueDate());

			String message = "";
			String messageService = "";

			if (intervalDaysService < 0) {
				messageService = "has passed";
				notifyService = true;
			} else if (intervalDaysService == 0) {
				messageService = "is today";
				notifyService = true;
			} else if (intervalDaysService == 1) {
				messageService = "is tomorrow";
				notifyService = true;
			} else if (intervalDaysService <= 15) {
				messageService = "due in" + intervalDays + " days.";
				notifyService = true;
			}

			if (intervalDays < 0) {
				message = "has expired";
				notify = true;
			} else if (intervalDays == 0) {
				message = "expires today";
				notify = true;
			} else if (intervalDays == 1) {
				message = "expires tomorrow";
				notify = true;
			} else if (intervalDays <= 15) {
				message = "expires in" + intervalDays + " days.";
				notify = true;
			}

			User admin = userRepository.findByEmail(email);
				if (notify) {
					Notification notification = new Notification("Insurance for " + vehicle.getVeName() + " (vehicleId: "
							+ vehicle.getVeId() + ") " + message);
					notification.setUser(admin);
					notificationRepository.save(notification);
				}
				if (notifyService) {
					Notification notification = new Notification("Service for " + vehicle.getVeName() + " (vehicleId: "
							+ vehicle.getVeId() + ") " + messageService);
					notification.setUser(admin);
					notificationRepository.save(notification);
				}

		}
	}
}
