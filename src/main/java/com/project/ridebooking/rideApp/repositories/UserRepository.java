package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
