package com.parkit.parkingsystem;

import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.DiscountCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountCalculatorServiceTest {

    private DiscountCalculatorService discountCalculatorService;

    @Mock
    private Ticket ticket;

    @BeforeEach
    private void setUpPerTest() {
    	discountCalculatorService = new DiscountCalculatorService();
    }

    @Test
    public void test_calculateDiscount_verifyTicketSetDiscount_whenRecurringUser(){

    	//GIVEN    	
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        
        //WHEN
        discountCalculatorService.calculateDiscount(ticket);
        discountCalculatorService.calculateDiscount(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setDiscount(0.05);
    }

    @Test
    public void test_calculateDiscount_verifyTicketSetDiscount_whenNoRecurringUser(){

    	//GIVEN    	
        when(ticket.getVehicleRegNumber()).thenReturn("ABCDEF");
        
        //WHEN
        discountCalculatorService.calculateDiscount(ticket);

        //THEN
        verify(ticket, Mockito.times(1)).setDiscount(0);
    }
}
