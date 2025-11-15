package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.Ride;

public interface  RideService {

    Ride getRideById( Long rideId);
    void matchWithDriver( RideRequestDto rideRequestDto );

}
