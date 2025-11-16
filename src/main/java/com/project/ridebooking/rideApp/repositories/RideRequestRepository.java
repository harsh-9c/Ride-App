package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
