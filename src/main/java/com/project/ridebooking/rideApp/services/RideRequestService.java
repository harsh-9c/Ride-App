package com.project.ridebooking.rideApp.services;


import com.project.ridebooking.rideApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideByRideRequestId(Long rideRequestId);
    void update(RideRequest rideRequest);
}
