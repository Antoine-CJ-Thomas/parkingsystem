package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class FareCalculatorServiceIT {

    private FareCalculatorService fareCalculatorService;
    private Ticket ticket;

    @BeforeEach
    private void setUpPerTest() {
        fareCalculatorService = new FareCalculatorService();
        ticket = new Ticket();
    }

    @Test
    public void test_calculateFare(){
    	
    	//GIVEN
        Date inTime = new Date();
    	Date outTime = DateUtils.addHours(inTime, 1);
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);

        //WHEN
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        
        //THEN
        assertEquals(ticket.getPrice(), Fare.CAR_RATE_PER_HOUR);
    }
}
