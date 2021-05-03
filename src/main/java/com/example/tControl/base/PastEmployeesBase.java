package com.example.tControl.base;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.tControl.pojo.Employee;
import com.example.tControl.pojo.PastEmployees;

public class PastEmployeesBase {
	
	public static List<PastEmployees>getAll() throws SQLException {

		EntityManager em = Base.getEntityManager();
		Query q = em.createQuery("FROM PastEmployees");
		List<PastEmployees> res = q.getResultList();
		return res;
	}

	public static Employee insertPastEmployee(int id, String tC, LocalDateTime timePassed) throws SQLException {
	    Employee e = EmployeesListBase.getEmployeeByID(id);
	    
	    EntityManager em = Base.getEntityManager();
	    	em.getTransaction().begin();
	    Query insertPastEmployee = em.createNativeQuery("INSERT INTO PastEmployees (fio,idCard,tC,passage,addDateTime) VALUES (?,?,?,?,?)");
	    insertPastEmployee.setParameter(1,e.getFio());
	    insertPastEmployee.setParameter(2,e.getIdCard());
	    insertPastEmployee.setParameter(3,tC);
	    insertPastEmployee.setParameter(4,"Проход №1");
	    insertPastEmployee.setParameter(5, Timestamp.valueOf(timePassed));
	    insertPastEmployee.executeUpdate();
	    	em.getTransaction().commit();
	    
		return e;
	}
}
