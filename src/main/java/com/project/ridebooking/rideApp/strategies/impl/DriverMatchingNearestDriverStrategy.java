package com.project.ridebooking.rideApp.strategies.impl;

import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.RideRequest;
import com.project.ridebooking.rideApp.repositories.DriverRepository;
import com.project.ridebooking.rideApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
