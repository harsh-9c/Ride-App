package com.project.ridebooking.rideApp.strategies.impl;

import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
