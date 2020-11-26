package com.parkit.parkingsystem;

import com.parkit.parkingsystem.util.InputReaderUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class InputReaderUtilTest {

	private InputReaderUtil inputReaderUtil;
	
    @BeforeEach
    private void setUpPerTest() {
    	inputReaderUtil = new InputReaderUtil();
    }

    @Test
    public void test_readSelection_equalConstantInt_whenScannerGetNumber(){
    	
    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream("3".getBytes()));
    	
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        
        //WHEN
        
        //THEN
        assertEquals(3, inputReaderUtil.readSelection());
    }

    @Test
    public void test_readSelection_equalConstantInt_whenScannerGetLetter(){
    	
    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream("THREE".getBytes()));
    	
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        
        //WHEN
        
        //THEN
        assertEquals(-1, inputReaderUtil.readSelection());
    }

    @Test
    public void test_readVehicleRegistrationNumber_equalConstantString_whenScannerGetCompltedString(){

    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream("3".getBytes()));
    	String vehicleRegNumber = null;
    	
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

        //WHEN
		try {
			vehicleRegNumber = inputReaderUtil.readVehicleRegistrationNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
           
		//THEN
        assertEquals("3", vehicleRegNumber);
    }

    @Test
    public void test_readVehicleRegistrationNumber_throwException_whenScannerGetSpaceString(){

    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream(" ".getBytes()));
    	
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

        //WHEN
        
        //THEN
        assertThrows(IllegalArgumentException.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
    }

    @Test
    public void test_readVehicleRegistrationNumber_throwException_whenScannerGetEmptyString(){

    	//GIVEN
    	Scanner scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    	
        try {
			FieldSetter.setField(inputReaderUtil, inputReaderUtil.getClass().getDeclaredField("scan"), scan);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

        //WHEN
        
        //THEN
        assertThrows(Exception.class, () -> inputReaderUtil.readVehicleRegistrationNumber());
    }
}
