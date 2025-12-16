package com.project.ridebooking.rideApp.controllers;

import com.project.ridebooking.rideApp.dto.RideDto;
import com.project.ridebooking.rideApp.dto.RideStartDto;
import com.project.ridebooking.rideApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideRequestId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.endRide(rideRequestId));
    }
}
