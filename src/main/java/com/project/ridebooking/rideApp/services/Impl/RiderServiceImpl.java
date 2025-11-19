package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RideDto;
import com.project.ridebooking.rideApp.dto.RideRequestDto;
import com.project.ridebooking.rideApp.entities.RideRequest;
import com.project.ridebooking.rideApp.entities.Rider;
import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.entities.enums.RideRequestStatus;
import com.project.ridebooking.rideApp.repositories.RideRequestRepository;
import com.project.ridebooking.rideApp.repositories.RiderRepository;
import com.project.ridebooking.rideApp.services.RiderService;
import com.project.ridebooking.rideApp.strategies.DriverMatchingStrategy;
import com.project.ridebooking.rideApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    public RiderServiceImpl(ModelMapper modelMapper,@Qualifier("rideFareDefaultFareCalculationStrategy") RideFareCalculationStrategy rideFareCalculationStrategy,
                            DriverMatchingStrategy driverMatchingStrategy,
                            RideRequestRepository rideRequestRepository,
                            RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.rideFareCalculationStrategy = rideFareCalculationStrategy;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.rideRequestRepository = rideRequestRepository;
        this.riderRepository = riderRepository;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        driverMatchingStrategy.findMatchingDrivers(rideRequest);

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
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
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);

    }
}
