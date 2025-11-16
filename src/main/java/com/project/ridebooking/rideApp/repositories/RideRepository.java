package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
