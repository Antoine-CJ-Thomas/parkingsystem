package com.parkit.parkingsystem;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.parkit.parkingsystem.config.DataBaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class DataBaseConfigTest {

    private static DataBaseConfig dataBaseConfig;
    
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    @BeforeEach
    private void initialization() {

    	//GIVEN
    	dataBaseConfig = new DataBaseConfig();
    }

    @Test
    public void test_getConnection_equalConnectionImplClass(){
   
        //THEN
        try {
			assertEquals(ConnectionImpl.class, dataBaseConfig.getConnection().getClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void test_closeConnection_verifyConnectionClose(){

    	//WHEN
    	dataBaseConfig.closeConnection(connection);

    	//THEN  	
    	try {
			verify(connection, times(1)).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void test_closePreparedStatement_verifyPreparedStatementClose(){

    	//WHEN
    	dataBaseConfig.closePreparedStatement(preparedStatement);

    	//THEN  	 	
    	try {
			verify(preparedStatement, times(1)).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    @Test
    public void test_closeResultSet_verifyResultSetClose(){
            	
    	//WHEN
    	dataBaseConfig.closeResultSet(resultSet);
    	
    	//THEN	 	
    	try {
			verify(resultSet, times(1)).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
