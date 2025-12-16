package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.entities.Payment;
import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.enums.PaymentStatus;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.repositories.PaymentRepository;
import com.project.ridebooking.rideApp.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;


    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .build();
        return paymentRepository.save(payment);
    }

}
