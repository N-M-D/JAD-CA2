package regionsDBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class regionDB {
	public ArrayList<Region> getRegions(){
		ArrayList<Region> regionList = new ArrayList<Region>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "SELECT * FROM regions";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Region r = new Region();
				r.setName(rs.getString("name"));
				regionList.add(r);
			}
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return regionList;
	}
}
