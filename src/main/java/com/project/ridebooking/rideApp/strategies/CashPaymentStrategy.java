package com.project.ridebooking.rideApp.strategies;

import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.Payment;
import com.project.ridebooking.rideApp.entities.Wallet;
import com.project.ridebooking.rideApp.entities.enums.PaymentStatus;
import com.project.ridebooking.rideApp.entities.enums.TransactionMethod;
import com.project.ridebooking.rideApp.repositories.PaymentRepository;
import com.project.ridebooking.rideApp.services.PaymentService;
import com.project.ridebooking.rideApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Total cost - 100
// Driver - 70 (30 will be platform commission)

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements  PaymentStrategy{

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), null, platformCommission, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
