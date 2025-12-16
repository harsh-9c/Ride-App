package com.project.ridebooking.rideApp.dto;

import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.Wallet;
import com.project.ridebooking.rideApp.entities.enums.TransactionMethod;
import com.project.ridebooking.rideApp.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {
    private Long id;

    private double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private Ride ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
