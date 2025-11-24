package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.entities.RideRequest;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.repositories.RideRequestRepository;
import com.project.ridebooking.rideApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideByRideRequestId(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride request not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride request not found with id: " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
