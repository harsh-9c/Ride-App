package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
