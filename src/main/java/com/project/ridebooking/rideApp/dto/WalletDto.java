package com.project.ridebooking.rideApp.dto;

import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransactionDto> transactions;
}
