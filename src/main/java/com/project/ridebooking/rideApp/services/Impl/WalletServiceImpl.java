package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.entities.Wallet;
import com.project.ridebooking.rideApp.entities.WalletTransaction;
import com.project.ridebooking.rideApp.entities.enums.TransactionMethod;
import com.project.ridebooking.rideApp.entities.enums.TransactionType;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.repositories.WalletRepository;
import com.project.ridebooking.rideApp.services.WalletService;
import com.project.ridebooking.rideApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, String transactionId, Double amount, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, String transactionId, Double amount, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: "+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: "+user.getId()));
    }
}
