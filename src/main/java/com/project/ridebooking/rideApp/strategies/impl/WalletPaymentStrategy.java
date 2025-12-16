package com.project.ridebooking.rideApp.strategies.impl;

import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.Payment;
import com.project.ridebooking.rideApp.entities.Rider;
import com.project.ridebooking.rideApp.entities.enums.PaymentStatus;
import com.project.ridebooking.rideApp.entities.enums.TransactionMethod;
import com.project.ridebooking.rideApp.repositories.PaymentRepository;
import com.project.ridebooking.rideApp.services.PaymentService;
import com.project.ridebooking.rideApp.services.WalletService;
import com.project.ridebooking.rideApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Rider had 232, Driver had 501
//Rider cost is 100, commission = 30
//Rider -> 232-100 = 132
//Driver -> 500 + (100-30) = 570



@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),null,payment.getAmount(),payment.getRide(), TransactionMethod.RIDE);
        double driverCut = payment.getAmount() * (1-PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), null,driverCut, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
