package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FareCalculatorServiceTest {

    private static FareCalculatorService fareCalculatorService;

    @Mock
    private ParkingSpot parkingSpot;
    @Mock
    private Ticket ticket;

    @BeforeEach
    private void initialization() {
        fareCalculatorService = new FareCalculatorService();
    }

    @Test
    public void test_calculateFare_verifyTicketSetPrice_whenParkingTypeCar(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addHours(inDate, 1);
    	
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.CAR);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);
        
        //WHEN
        fareCalculatorService.calculateFare(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setPrice(Fare.CAR_RATE_PER_HOUR);
    }

    @Test
    public void test_calculateFare_verifyTicketSetPrice_whenParkingTypeBike(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addHours(inDate, 1);
    	
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.BIKE);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);

        //WHEN
        fareCalculatorService.calculateFare(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setPrice(Fare.BIKE_RATE_PER_HOUR);
    }

    @Disabled
    @Test
    public void test_calculateFare_throwException_whenParkingTypeUnknow(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addHours(inDate, 1);

        when(parkingSpot.getParkingType()).thenReturn(null);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);
        
        //WHEN

        //THEN
        assertThrows(NullPointerException.class, () -> fareCalculatorService.calculateFare(ticket));
    }

    @Test
    public void test_calculateFare_verifyTicketSetPrice_whenParkingTypeCarLessOneHour(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addMinutes(inDate, 45);
    	
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.CAR);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);

        //WHEN
        fareCalculatorService.calculateFare(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setPrice(Fare.CAR_RATE_PER_HOUR*0.75);
    }

    @Test
    public void test_calculateFare_verifyTicketSetPrice_whenParkingTypeBikeLessOneHour(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addMinutes(inDate, 45);
    	
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.BIKE);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);

        //WHEN
        fareCalculatorService.calculateFare(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setPrice(Fare.BIKE_RATE_PER_HOUR*0.75);
    }

    @Test
    public void test_calculateFare_verifyTicketSetPrice_whenParkingTypeCarMoreOneDay(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addHours(inDate, 24);
    	
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.CAR);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);

        //WHEN
        fareCalculatorService.calculateFare(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setPrice(Fare.CAR_RATE_PER_HOUR*24);
    }

    @Test
    public void test_calculateFare_throwException_whenIntimeSuperiorOutTime(){

    	//GIVEN
    	Date outDate = new Date();
    	Date inDate = DateUtils.addHours(outDate, 1);

        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);
        
        //WHEN
        
        //THEN
        assertThrows(IllegalArgumentException.class, () -> fareCalculatorService.calculateFare(ticket));
    }
}
