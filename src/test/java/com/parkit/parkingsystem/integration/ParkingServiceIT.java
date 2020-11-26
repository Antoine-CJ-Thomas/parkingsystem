package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceIT {

	private ParkingService parkingService;
    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        dataBasePrepareService.clearDataBaseEntries();
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    }

    @Test
    public void test_processIncomingVehicle(){
    	
    	//GIVEN
        try {
            when(inputReaderUtil.readSelection()).thenReturn(1);
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //WHEN
        parkingService.processIncomingVehicle();
        
        //THEN
        //TODO: check that a ticket is actually saved in DB and Parking table is updated with availability
        assertEquals(false, ticketDAO.getTicket("ABCDEF").getParkingSpot().isAvailable());
    }

    @Test
    public void test_processExitingVehicle(){
    	    	
    	//GIVEN
        Date date = DateUtils.addHours(new Date(), -1);
        Ticket ticket = new Ticket();
        
        ticket.setParkingSpot(new ParkingSpot(1,ParkingType.CAR, true));
        ticket.setVehicleRegNumber("UVWXYZ");
        ticket.setPrice(0);
        ticket.setInTime(date);
        ticket.setOutTime(null);
        
        try {
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("UVWXYZ");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //WHEN
        ticketDAO.saveTicket(ticket);                
        parkingService.processExitingVehicle();
        
        //THEN
        //TODO: check that the fare generated and out time are populated correctly in the database
        assertEquals(true, ticketDAO.getTicket("UVWXYZ").getPrice()>0 && ticketDAO.getTicket("UVWXYZ").getOutTime() != null);
    }
}
