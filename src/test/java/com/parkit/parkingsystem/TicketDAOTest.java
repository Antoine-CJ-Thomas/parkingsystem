package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

@ExtendWith(MockitoExtension.class)
public class TicketDAOTest {

    private static TicketDAO ticketDAO;
    
	@Mock
	private Ticket ticket;
	@Mock
	private ParkingSpot parkingSpot;
    
    @BeforeEach
    private void initialization() {
    	ticketDAO = new TicketDAO();
    }

    @Disabled
    @Test
    public void test_saveTicket_equalTrue(){

    	//GIVEN
        when(parkingSpot.getId()).thenReturn(1);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        when(ticket.getPrice()).thenReturn(1.0);
        when(ticket.getInTime()).thenReturn(new Date());
        when(ticket.getOutTime()).thenReturn(new Date());

    	//WHEN
    	
        //THEN
        assertEquals(true, ticketDAO.saveTicket(ticket));
    }

    @Test
    public void test_saveTicket_equalFalse(){

    	//GIVEN
        when(parkingSpot.getId()).thenReturn(1);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        when(ticket.getPrice()).thenReturn(1.0);
        when(ticket.getInTime()).thenReturn(new Date());
        when(ticket.getOutTime()).thenReturn(new Date());

    	//WHEN
    	
        //THEN
        assertEquals(false, ticketDAO.saveTicket(ticket));
    }

    @Test
    public void test_getTicket_equalConstantString(){

    	//GIVEN
        when(parkingSpot.getId()).thenReturn(1);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        when(ticket.getPrice()).thenReturn(1.0);
        when(ticket.getInTime()).thenReturn(new Date());
        when(ticket.getOutTime()).thenReturn(new Date());
        
        //WHEN
        ticketDAO.saveTicket(ticket);

        //THEN      
        assertEquals("ABCDEF", ticketDAO.getTicket(ticket.getVehicleRegNumber()).getVehicleRegNumber());
    }

    @Test
    public void test_getTicket_equalNull(){

    	//GIVEN
        when(parkingSpot.getId()).thenReturn(1);
        when(ticket.getParkingSpot()).thenReturn(parkingSpot);
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        when(ticket.getPrice()).thenReturn(1.0);
        when(ticket.getInTime()).thenReturn(new Date());
        when(ticket.getOutTime()).thenReturn(new Date());
        
        //WHEN
        ticketDAO.saveTicket(ticket);

        //THEN      
        assertEquals(null, ticketDAO.getTicket("Unknow ticket"));
    }

    @Test
    public void test_updateTicket_equalTrue(){

    	//GIVEN
        when(ticket.getId()).thenReturn(1);
        when(ticket.getPrice()).thenReturn(1.0);
        when(ticket.getOutTime()).thenReturn(new Date());

    	//WHEN
        
        //THEN
        assertEquals(true, ticketDAO.updateTicket(ticket));
    }

    @Test
    public void test_updateTicket_equalFalse(){

    	//GIVEN

    	//WHEN
        
        //THEN
        assertEquals(false, ticketDAO.updateTicket(null));
    }
}
