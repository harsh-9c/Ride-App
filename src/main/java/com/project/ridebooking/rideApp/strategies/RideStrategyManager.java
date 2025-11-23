package com.project.ridebooking.rideApp.strategies;

import com.project.ridebooking.rideApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.project.ridebooking.rideApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.project.ridebooking.rideApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.project.ridebooking.rideApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating>=4.8){
            return highestRatedDriverStrategy;
        } else {
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
//        6PM to 9PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;
        }
        else{
            return defaultFareCalculationStrategy;
        }
    }

}
