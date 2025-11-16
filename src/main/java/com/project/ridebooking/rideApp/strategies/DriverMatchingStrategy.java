package com.project.ridebooking.rideApp.strategies;

import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto);
}
