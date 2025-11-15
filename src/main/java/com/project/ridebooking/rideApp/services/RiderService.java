package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RideDto;
import com.project.ridebooking.rideApp.dto.RideRequestDto;

import java.util.List;

public interface RiderService {
    RideRequestDto acceptRide(RideRequestDto rideRequestDto);
    RideDto startRide( Long rideId);
    RideDto cancelRide( Long rideId);
    RideDto endRide( Long rideId);
    RideDto rateDriver( Long rideId, Integer rating);
    DriverDto getMyProfile();
    List<RideDto> getAllMyRides();
}
