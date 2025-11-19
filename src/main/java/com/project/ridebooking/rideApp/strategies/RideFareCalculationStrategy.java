package com.project.ridebooking.rideApp.strategies;

import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.RideRequest;

public interface RideFareCalculationStrategy {

    static final double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
