package com.parkit.parkingsystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.model.ParkingSpot;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOIT {

    private ParkingSpotDAO parkingSpotDAO;
	private ParkingSpot parkingSpot;
    
    @BeforeEach
    private void setUpPerTest() {
    	parkingSpotDAO = new ParkingSpotDAO();
    	parkingSpot = new ParkingSpot(1,ParkingType.CAR,true);
    }

    @Test
    public void test_getNextAvailableSlot_equelConstantInt_WhenParkingTypeCar(){
    	
        //GIVEN

        //WHEN
        
    	//THEN
        assertEquals(1, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    public void test_updateParking_equalTrue(){
    	
        //GIVEN

        //WHEN

    	//THEN
        assertEquals(true, parkingSpotDAO.updateParking(parkingSpot));
    }
}
