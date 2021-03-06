package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private ParkingService parkingService;

    @Mock
    private InputReaderUtil inputReaderUtil;
    @Mock
    private ParkingSpotDAO parkingSpotDAO;
    @Mock
    private TicketDAO ticketDAO;
    @Mock
    private ParkingSpot parkingSpot;
    @Mock
    private Ticket ticket;

    @BeforeEach
    private void setUpPerTest() {
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    }

    @Test
    public void test_processIncomingVehicle_verifyTicketDAOSaveTicket(){

    	//GIVEN
        try {
        	
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
    	
        //WHEN
        parkingService.processIncomingVehicle();
        
        //THEN
        verify(ticketDAO, Mockito.times(1)).saveTicket(any(Ticket.class));
    }

    @Test
    public void test_getNextParkingNumberIfAvailable_equalParkingSpotClass(){

    	//GIVEN
        when(inputReaderUtil.readSelection()).thenReturn(2);
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(2);
    	
        //WHEN

        //THEN
        assertEquals(ParkingSpot.class, parkingService.getNextParkingNumberIfAvailable().getClass());
    }

    @Test
    public void test_processExitingVehicle_verifyParkingSpotDAOUpdateParking(){

    	//GIVEN
    	Date inDate = new Date();
    	Date outDate = DateUtils.addHours(inDate, 1);
             
        try {
            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
        when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);
        when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
        when(parkingSpot.getParkingType()).thenReturn(ParkingType.CAR);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getInTime()).thenReturn(inDate);
        when(ticket.getOutTime()).thenReturn(outDate);
        
        //WHEN
        parkingService.processExitingVehicle();
        
        //THEN
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
    }

}
