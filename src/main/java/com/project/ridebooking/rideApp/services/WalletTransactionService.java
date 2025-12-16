package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.dto.WalletTransactionDto;
import com.project.ridebooking.rideApp.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
