package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.entities.Payment;
import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
}
