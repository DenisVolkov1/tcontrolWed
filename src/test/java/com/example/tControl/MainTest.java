package com.example.tControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.example.tControl.base.ConnectionPool;
import com.example.tControl.pojo.DataArrayExamples;
import com.example.tControl.pojo.Employee;
import com.example.tControl.pojo.PastEmployees;

public class MainTest {

	public static void main(String[] args) throws SQLException {
			
		
		ResultSet rsObj = null;
	    PreparedStatement pstmtObj = null;
	    
	    try (Connection con = ConnectionPool.getConnection()){   
	        // Performing Database Operation!
	        System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
	
	        pstmtObj = con.prepareStatement("SELECT * FROM EmployeesList");
	        rsObj = pstmtObj.executeQuery();
	        while (rsObj.next()) {
	            System.out.println("Username: " + rsObj.getString("fio"));
	        }
	    }
	    
	    
	}
	
}
