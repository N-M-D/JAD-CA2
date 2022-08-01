package toursDBAccess;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

public class TourDB {
	public int createTour(String tourName, String tourDesc, String tourDetailed, float tourCost, int tourSlots, int tourType, String tourImg) {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
			//out.print(sqlStr);
			String sqlStr = "INSERT INTO tour (tourName, tourDescription, tourDetailed, tourCost, tourSlots, tourType, tourPicture) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, tourName);
			pstmt.setString(2, tourDesc);
			pstmt.setString(3, tourDetailed);
			pstmt.setFloat(4, tourCost);
			pstmt.setInt(5, tourSlots);
			pstmt.setInt(6, tourType);
			pstmt.setString(7, tourImg);
			//pstmt.setString(2, password);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
			//if(verified){
			//	String userRole = "adminUser";
			//	session.setAttribute("sessUserID", loginid);
			//	session.setAttribute("sessUserRole", userRole);
			//	session.setAttribute("loginStatus", "success");
			//	response.sendRedirect("displayMember.jsp?userId=" + loginid + "&userRole=" + userRole	);
			//}else{
			//	response.sendRedirect("login.jsp?errCode=invalidLogin");
			//}
		} 
		catch(SQLIntegrityConstraintViolationException e){
			System.out.println("Error :");
		}
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return rows;
	}
	
	public ArrayList<Tour> getTours() {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
			//out.print(sqlStr);
			String sqlStr = "SELECT * FROM tour";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			//pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tourList.add(tour);
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return tourList;
	}
	
	public ArrayList<Tour> getToursByCategory(int catid) {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
			//out.print(sqlStr);
			String sqlStr = "SELECT * FROM tour WHERE tourType=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, catid);
			//pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tourList.add(tour);
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return tourList;
	}
	
	public int updateTour(int tourID, String tourName, String tourDesc, String tourDetailed, float tourCost, int tourSlots, int tourType, String tourImg) {
		int rows = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
			//out.print(sqlStr);
			String sqlStr = "UPDATE tour SET tourName=?, tourDescription=?, tourDetailed=?, tourCost=?, tourSlots=?, tourType=?, tourPicture=? WHERE tourID=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, tourName);
			pstmt.setString(2, tourDesc);
			pstmt.setString(3, tourDetailed);
			pstmt.setFloat(4, tourCost);
			pstmt.setInt(5, tourSlots);
			pstmt.setInt(6, tourType);
			pstmt.setString(7, tourImg);
			pstmt.setInt(8, tourID);
			//pstmt.setString(2, password);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return rows;
	}
	
	public boolean deleteTour(int tourID) {
		boolean success = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
			//out.print(sqlStr);
			String sqlStr = "DELETE FROM tour WHERE tourID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, tourID);
			//pstmt.setString(2, password);
			success = pstmt.execute();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
}

