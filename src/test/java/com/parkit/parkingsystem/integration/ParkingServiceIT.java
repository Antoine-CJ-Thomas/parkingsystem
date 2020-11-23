package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceIT {

	private static ParkingService parkingService;
    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static DataBasePrepareService dataBasePrepareService = new DataBasePrepareService();
    private static InputReaderUtil inputReaderUtil = new InputReaderUtil();
    private static ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
    private static TicketDAO ticketDAO = new TicketDAO();


    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        dataBasePrepareService.clearDataBaseEntries();
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    }

    @Test
    public void test_processIncomingVehicle(){
    	
    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream(("1\nABCDEF").getBytes()));
        
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
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
    	Scanner scan = new Scanner(new ByteArrayInputStream(("1\nABCDEF\nABCDEF").getBytes()));
        
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        
        //WHEN
        parkingService.processIncomingVehicle();
        parkingService.processExitingVehicle();
        
        //THEN
        //TODO: check that the fare generated and out time are populated correctly in the database
        assertEquals(true, ticketDAO.getTicket("ABCDEF").getPrice()>0 && ticketDAO.getTicket("ABCDEF").getOutTime() != null);
    }
}
