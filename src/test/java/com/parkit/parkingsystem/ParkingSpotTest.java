package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotTest {

	private static ParkingSpot parkingSpot;

    @BeforeEach
    private void initialization() {

    	//GIVEN
    	parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
    }

    @Test
    public void test_setAndGetID_equalConstantInt(){
        
    	//WHEN
    	parkingSpot.setId(3);
    	
    	//THEN
        assertEquals(3, parkingSpot.getId());
    }

    @Test
    public void test_setAndGetParkingType_equalParkingTypeCar(){

    	//WHEN
    	parkingSpot.setParkingType(ParkingType.CAR);

    	//THEN
        assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
    }

    @Test
    public void test_setAndGetParkingType_equalParkingTypeBike(){

    	//WHEN
    	parkingSpot.setParkingType(ParkingType.BIKE);

    	//THEN
        assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
    }

    @Test
    public void test_setAndGetAvailability_equalTrue(){

    	//WHEN
    	parkingSpot.setAvailable(true);

    	//THEN
        assertEquals(true, parkingSpot.isAvailable());
    }

    @Test
    public void test_equals_equalTrue_whenHimself(){

    	//THEN
        assertEquals(true, parkingSpot.equals(parkingSpot));
    }

    @Test
    public void test_equals_equalTrue_whenAnotherInstance(){

    	//THEN
        assertEquals(true, parkingSpot.equals(new ParkingSpot(1,ParkingType.CAR,false)));
    }

    @Test
    public void test_equals_equalFalse_whenNull(){

    	//THEN
        assertEquals(false, parkingSpot.equals(null));
    }

    @Test
    public void test_equals_equalFalse_whenAnotherClass(){

    	//THEN
        assertEquals(false, parkingSpot.equals(new String()));
    }

    @Test
    public void test_hashCode_equalConstantInt(){

    	//WHEN
    	parkingSpot.setId(4);

    	//THEN
        assertEquals(4, parkingSpot.hashCode());
    }
}
