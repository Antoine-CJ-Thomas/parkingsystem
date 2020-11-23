package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.model.ParkingSpot;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOTest {

    private static ParkingSpotDAO parkingSpotDAO;

	@Mock
	private ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
    
    @BeforeEach
    private void initialization() {

    	//GIVEN
    	parkingSpotDAO = new ParkingSpotDAO();
    }

    @Test
    public void test_getNextAvailableSlot_equelConstantInt_WhenParkingTypeCar(){
        
    	//THEN
        assertEquals(1, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    public void test_getNextAvailableSlot_equelConstantInt_WhenParkingTypeBike(){
        
    	//THEN
        assertEquals(4, parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE));
    }

    @Disabled
    @Test
    public void test_updateParking_equalTrue(){

    	//THEN
        assertEquals(true, parkingSpotDAO.updateParking(parkingSpot));
    }

    @Test
    public void test_updateParking_equalFalse(){

    	//THEN
        assertEquals(false, parkingSpotDAO.updateParking(parkingSpot));
    }
}
