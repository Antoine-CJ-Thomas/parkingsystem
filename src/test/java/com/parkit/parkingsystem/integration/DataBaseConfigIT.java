package com.parkit.parkingsystem.integration;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class DataBaseConfigIT {

    private DataBaseConfig dataBaseConfig;
    
    @BeforeEach
    private void setUpPerTest() {
    	dataBaseConfig = new DataBaseTestConfig();
    }

    @Test
    public void test_getConnection_equalConnectionImplClass(){

    	//GIVEN

    	//WHEN
   
        //THEN
        try {
			assertEquals(ConnectionImpl.class, dataBaseConfig.getConnection().getClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
