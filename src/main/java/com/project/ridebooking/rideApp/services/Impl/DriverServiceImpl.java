package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RideDto;
import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.RideRequest;
import com.project.ridebooking.rideApp.entities.enums.RideRequestStatus;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.repositories.DriverRepository;
import com.project.ridebooking.rideApp.services.DriverService;
import com.project.ridebooking.rideApp.services.RideRequestService;
import com.project.ridebooking.rideApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideByRideRequestId(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request cannot be accepted, status is: "+rideRequest.getRideRequestStatus());
        }
        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()){
            throw new RuntimeException("Driver not available");
        }

        Ride ride = rideService.createNewRide(rideRequest,driver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L)
                .orElseThrow(()->new ResourceNotFoundException("Driver not found wiht id: 2"));
    }
}
