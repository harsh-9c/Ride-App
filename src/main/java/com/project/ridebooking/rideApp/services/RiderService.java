package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RideDto;
import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.Rider;
import com.project.ridebooking.rideApp.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide( Long rideId);
    RideDto rateDriver( Long rideId, Integer rating);
    DriverDto getMyProfile();
    List<RideDto> getAllMyRides();
    Rider createNewRider(User user);
}
