package com.parkit.parkingsystem.service;

import java.util.ArrayList;

import com.parkit.parkingsystem.model.Ticket;

public class DiscountCalculatorService {

	private ArrayList<String> vehichleRegNumberList = new ArrayList<String>();
	
    public void calculateDiscount(Ticket ticket){
    	
    	double discount = 0;
    	
		if (vehichleRegNumberList.contains(ticket.getVehicleRegNumber()) == false) {

			vehichleRegNumberList.add(ticket.getVehicleRegNumber());
		}

		else {
			
			discount = 0.05;
            System.out.println("Welcome back! As a recurring user of our parking lot, you'll benefit from a 5% discount");
		}
	
		ticket.setDiscount(discount);
    }
}