package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.DiscountCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorServiceIT {

    private DiscountCalculatorService discountCalculatorService;
    private Ticket ticket;

    @BeforeEach
    private void setUpPerTest() {
    	discountCalculatorService = new DiscountCalculatorService();
        ticket = new Ticket();
    }

    @Test
    public void test_calculateDiscount_verifyTicketSetDiscount_whenRecurringUser(){

    	//GIVEN    	
        
        //WHEN
        ticket.setVehicleRegNumber("ABCDEF");
        discountCalculatorService.calculateDiscount(ticket);
        discountCalculatorService.calculateDiscount(ticket);

        //THEN
        assertEquals(ticket.getDiscount(), 0.05);
    }
}
