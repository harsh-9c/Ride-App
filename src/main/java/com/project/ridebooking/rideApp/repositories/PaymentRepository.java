package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Payment;
import com.project.ridebooking.rideApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
