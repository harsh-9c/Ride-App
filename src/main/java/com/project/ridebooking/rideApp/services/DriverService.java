package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RideDto;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideId);
    RideDto startRide( Long rideId);
    RideDto cancelRide( Long rideId);
    RideDto endRide( Long rideId);
    RideDto rateDriver( Long rideId, Integer rating);
    DriverDto getMyProfile();
    List<RideDto> getAllMyRides();
}
