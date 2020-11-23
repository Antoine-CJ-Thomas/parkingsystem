package com.parkit.parkingsystem;

import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TicketTest {

	private static Ticket ticket;
	
	@Mock
	private ParkingSpot parkingSpot;

    @BeforeEach
    private void initialization() {

    	//GIVEN
    	ticket = new Ticket();
    }

    @Test
    public void test_setAndGetID_equalConstantInt(){
        
    	//WHEN
    	ticket.setId(3);
    	
    	//THEN
        assertEquals(3, ticket.getId());
    }

    @Test
    public void test_setAndGetParkingSpot_equalMockedParkingSpot(){
        
    	//WHEN
    	ticket.setParkingSpot(parkingSpot);

    	//THEN
        assertEquals(parkingSpot, ticket.getParkingSpot());
    }

    @Test
    public void test_setAndGetVehiculeRegNumber_equalConstantString(){

    	//WHEN
    	ticket.setVehicleRegNumber("ABCDEF");

    	//THEN
        assertEquals("ABCDEF", ticket.getVehicleRegNumber());
    }

    @Test
    public void test_setAndGetPrice_equalConstantInt(){

    	//WHEN
    	ticket.setPrice(4);

    	//THEN
        assertEquals(4, ticket.getPrice());
    }

    @Test
    public void test_setAndGetInTime_equalNewDate(){

    	//GIVEN
    	Date date = new Date();

    	//WHEN
    	ticket.setInTime(date);

    	//THEN
        assertEquals(date, ticket.getInTime());
    }

    @Test
    public void test_setAndGetOutTime_equalNewDate(){
        
    	//GIVEN
    	Date date = new Date();

    	//WHEN
    	ticket.setOutTime(date);

    	//THEN
        assertEquals(date, ticket.getOutTime());
    }
}