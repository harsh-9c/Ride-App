package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.dto.WalletTransactionDto;
import com.project.ridebooking.rideApp.entities.WalletTransaction;
import com.project.ridebooking.rideApp.repositories.WalletTransactionRepository;
import com.project.ridebooking.rideApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private  final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }

}
