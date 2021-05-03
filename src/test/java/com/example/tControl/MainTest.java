package com.example.tControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.example.tControl.base.ConnectionPool;
import com.example.tControl.base.EmployeesListBase;
import com.example.tControl.base.PastEmployeesBase;
import com.example.tControl.pojo.DataArrayExamples;
import com.example.tControl.pojo.Employee;
import com.example.tControl.pojo.PastEmployees;

public class MainTest {

	  
	  
	public static void main(String[] args) throws SQLException {
			
		
		//System.out.println(EmployeesListBase.getEmployeeByID(107));
		
		//PastEmployeesBase.insertPastEmployee(106, "40.7", LocalDateTime.now());
		
		System.out.println(PastEmployeesBase.getAll());
		System.out.println(EmployeesListBase.getAll());
		
	
	    
	}
	

	 
}
