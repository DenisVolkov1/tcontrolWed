package com.example.tControl.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.tControl.pojo.Employee;
import com.example.tControl.views.EmployeesList;

public class EmployeesListBase {
	
	public static List<Employee> getAll() throws SQLException {
		ResultSet rsObj = null;
	    PreparedStatement pstmtObj = null;
	    List<Employee> res = new ArrayList<Employee>();
	    try (Connection con = ConnectionPool.getConnection()) {
	        pstmtObj = con.prepareStatement("SELECT * FROM tcontrolbase.EmployeesList;");
	        rsObj = pstmtObj.executeQuery();
	        while (rsObj.next()) {

	        	 String personnelNumber = rsObj.getString("personnelNumber");
	        	 String fio = rsObj.getString("fio");
	        	 String idCard = rsObj.getString("idCard");
	        	 String division = rsObj.getString("division");
	        	 String position = rsObj.getString("position");
	        	
	        	res.add(new Employee(personnelNumber, fio, idCard, division, position));
	        }
	    }
		
		return res;
		
	}

}
