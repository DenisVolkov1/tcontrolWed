package com.example.tControl.base;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.compress.utils.IOUtils;

import com.example.tControl.pojo.Employee;



public class EmployeesListBase {
	
	public static List<Employee> getAll() throws SQLException {
	    
		EntityManager em = Base.getEntityManager();
		Query q = em.createQuery("FROM Employee");
		List<Employee> res = q.getResultList();
		
		return res;
	}
	public static Employee getEmployeeByID(int id) throws SQLException {
		
    	EntityManager em = Base.getEntityManager();
    	TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1", Employee.class);
    	Employee res = query.setParameter(1, id).getSingleResult();
		return res;
	}
	
	public static void insertEmployee(String personnelNumber, String fio, String idCard, String division, String position, InputStream photoStream) throws SQLException, IOException {

	    EntityManager em = Base.getEntityManager();
    	em.getTransaction().begin();
    Query insertEmployee = em.createNativeQuery("INSERT INTO EmployeesList (personnelNumber,fio,idCard,division,position,photoByteArray,AddDateTime) VALUES (?,?,?,?,?,?,?)");
    insertEmployee.setParameter(1,personnelNumber);
    insertEmployee.setParameter(2,fio);
    insertEmployee.setParameter(3,idCard);
    insertEmployee.setParameter(4,division);
    insertEmployee.setParameter(5,position);
    insertEmployee.setParameter(6, IOUtils.toByteArray(photoStream));
    insertEmployee.setParameter(7,Timestamp.valueOf(LocalDateTime.now()));
    insertEmployee.executeUpdate();
    	em.getTransaction().commit();
	}
	
	
	

}
