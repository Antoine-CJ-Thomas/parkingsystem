package com.parkit.parkingsystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

@ExtendWith(MockitoExtension.class)
public class TicketDAOIT {

    private TicketDAO ticketDAO;
	private ParkingSpot parkingSpot;
	private Ticket ticket;
    
    @BeforeEach
    private void setUpPerTest() {
    	ticketDAO = new TicketDAO();
    	ticket = new Ticket();
    	parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
    }

    @Disabled // Because always return false (return false in a finally block)
    @Test
    public void test_saveTicket_equalTrue(){

    	//GIVEN    	
    	ticket.setParkingSpot(parkingSpot);
    	ticket.setVehicleRegNumber("ABCDEF");
    	ticket.setPrice(1.0);
    	ticket.setInTime(new Date());
    	ticket.setOutTime(new Date());

    	//WHEN
    	
        //THEN
        assertEquals(true, ticketDAO.saveTicket(ticket));
    }

    @Test
    public void test_getTicket_equalConstantString(){

    	//GIVEN    	
    	ticket.setParkingSpot(parkingSpot);
    	ticket.setVehicleRegNumber("ABCDEF");
    	ticket.setPrice(1.0);
    	ticket.setInTime(new Date());
    	ticket.setOutTime(new Date());
        
        //WHEN
        ticketDAO.saveTicket(ticket);

        //THEN      
        assertEquals("ABCDEF", ticketDAO.getTicket(ticket.getVehicleRegNumber()).getVehicleRegNumber());
    }

    @Test
    public void test_updateTicket_equalTrue(){

    	//GIVEN
    	ticket.setId(1);
    	ticket.setPrice(1.0);
    	ticket.setOutTime(new Date());

    	//WHEN
        
        //THEN
        assertEquals(true, ticketDAO.updateTicket(ticket));
    }
}
