package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.Rating;
import com.project.ridebooking.rideApp.entities.Ride;
import com.project.ridebooking.rideApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
