package com.example.tControl.base;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPool {
	
	 // JDBC Driver Name & Database URL  
    static  String JDBC_DB_URL = "jdbc:mysql://localhost:3306/tcontrolbase?serverTimezone=UTC";
 
    // JDBC Database Credentials
    static  String JDBC_USER = "root";
    static  String JDBC_PASS = "admin";
 
    private static DataSource dataSource = null;
    
    static {
    	 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    
    private ConnectionPool() {}
    
    public static Connection getConnection() throws SQLException {
  
		return (dataSource != null) ?  dataSource.getConnection() : newConnectionPool();

    }

	private static Connection newConnectionPool() throws SQLException {
		GenericObjectPool gPool = new GenericObjectPool();
		gPool.setMaxActive(50);
		   ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
		   PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
		   dataSource = new PoolingDataSource(gPool);
		   return dataSource.getConnection();
	}
    
    
	

}
