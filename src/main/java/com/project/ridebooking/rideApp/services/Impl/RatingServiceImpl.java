package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.RiderDto;
import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.Rating;
import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.Rider;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.exceptions.RuntimeConflictException;
import com.project.ridebooking.rideApp.repositories.DriverRepository;
import com.project.ridebooking.rideApp.repositories.RatingRepository;
import com.project.ridebooking.rideApp.repositories.RiderRepository;
import com.project.ridebooking.rideApp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
        if(ratingObj.getDriverRating()!=null){
            throw new RuntimeConflictException("Driver has already been rated. Cannot rate again!");
        }
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(r -> r.getDriverRating() == null ? 0.0 : r.getDriverRating())
                .average()
                .orElse(0.0);
        driver.setRating(newRating);
        driverRepository.save(driver);
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
        if(ratingObj.getRiderRating()!=null){
            throw new RuntimeConflictException("Rider has already been rated. Cannot rate again!");
        }
        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(r -> r.getRiderRating() == null ? 0.0 : r.getRiderRating())
                .average()
                .orElse(0.0);
        rider.setRating(newRating);
        riderRepository.save(rider);
        return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .ride(ride)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .build();
        ratingRepository.save(rating);
    }
}
