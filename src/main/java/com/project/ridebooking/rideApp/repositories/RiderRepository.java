package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.Rider;
import com.project.ridebooking.rideApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
