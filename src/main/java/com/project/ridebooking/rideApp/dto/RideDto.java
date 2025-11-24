package com.project.ridebooking.rideApp.dto;

import com.project.ridebooking.rideApp.entities.Driver;
import com.project.ridebooking.rideApp.entities.enums.PaymentMethod;
import com.project.ridebooking.rideApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {

    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private String otp;
    private  Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}

