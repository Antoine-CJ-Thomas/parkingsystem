package com.parkit.parkingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.service.InteractiveShell;

@ExtendWith(MockitoExtension.class)
public class InteractiveShellTest {

    private static InteractiveShell intercativeShell;

    @BeforeEach
    private void initialization() {

    	//GIVEN
    	intercativeShell = new InteractiveShell();
    }

    @Disabled
    @Test
    public void test_loadInterface(){
        
    	//GIVEN
    	
    	//WHEN
    	intercativeShell.loadInterface();
    	
    	//THEN
    	
    }
}
