package com.project.ridebooking.rideApp.strategies;

import com.project.ridebooking.rideApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);
}
