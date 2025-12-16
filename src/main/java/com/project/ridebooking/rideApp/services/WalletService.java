package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.entities.Wallet;
import com.project.ridebooking.rideApp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, String transactionId, Double amount, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, String transactionId, Double amount, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
