package com.example.tControl.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.example.tControl.pojo.PastEmployees;

public class SettingsBase {
	
	public static String getTimeZone() throws SQLException {
		ResultSet rsObj = null;
		PreparedStatement pstmtObj = null;
		String zoneOffset_timeZone = null;

		    try (Connection con = ConnectionPool.getConnection()) {
		        pstmtObj = con.prepareStatement("SELECT * FROM tcontrolbase.Settings WHERE nameParam = ?;");
		        pstmtObj.setString(1, "ZoneOffset_timeZone");
		        rsObj = pstmtObj.executeQuery();
		        rsObj.next();
		        	zoneOffset_timeZone = rsObj.getString("value1");
		    }
			return zoneOffset_timeZone;
	}

}
