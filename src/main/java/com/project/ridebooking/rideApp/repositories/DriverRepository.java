package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
