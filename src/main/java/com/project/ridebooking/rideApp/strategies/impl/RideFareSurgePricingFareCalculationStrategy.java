package com.project.ridebooking.rideApp.strategies.impl;

import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.RideRequest;
import com.project.ridebooking.rideApp.services.DistanceService;
import com.project.ridebooking.rideApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }

}
