package com.cognizant.vehiclereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.vehiclereservationsystem.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
