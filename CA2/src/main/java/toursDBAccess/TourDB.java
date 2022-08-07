package toursDBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class TourDB {
	public int createTour(String tourName, String tourDesc, String tourDetailed, float tourCost, int tourSlots, int tourType, String tourImg, String tourDate) {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "INSERT INTO tour (tourName, tourDescription, tourDetailed, tourCost, tourSlots, tourType, tourPicture, tourDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, tourName);
			pstmt.setString(2, tourDesc);
			pstmt.setString(3, tourDetailed);
			pstmt.setFloat(4, tourCost);
			pstmt.setInt(5, tourSlots);
			pstmt.setInt(6, tourType);
			pstmt.setString(7, tourImg);
			pstmt.setString(8, tourDate);
			//pstmt.setString(2, password);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
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
			String sqlStr = "SELECT * FROM tour";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			//pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourID(rs.getInt("tourID"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourSlots(rs.getInt("tourSlots"));
				tour.setTourType(rs.getInt("tourType"));
				tour.setTourImg(rs.getString("tourPicture"));
				tour.setTourDate(rs.getString("tourDate"));
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
	
	public Tour getTour(int tourID) {
		Tour tour = new Tour();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM tour WHERE tourID=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, tourID);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				tour.setTourID(rs.getInt("tourID"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourSlots(rs.getInt("tourSlots"));
				tour.setTourType(rs.getInt("tourType"));
				tour.setTourImg(rs.getString("tourPicture"));
				tour.setTourDate(rs.getString("tourDate"));
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return tour;
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
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourDate(rs.getString("tourDate"));
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
	
	public ArrayList<Tour> getToursByPrice() {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM tour order by tourCost asc";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			//pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourID(rs.getInt("tourID"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourSlots(rs.getInt("tourSlots"));
				tour.setTourType(rs.getInt("tourType"));
				tour.setTourImg(rs.getString("tourPicture"));
				tour.setTourDate(rs.getString("tourDate"));
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
	
	public int updateTour(int tourID, String tourName, String tourDesc, String tourDetailed, float tourCost, int tourSlots, int tourType, String tourImg, String tourDate) {
		int rows = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "UPDATE tour SET tourName=?, tourDescription=?, tourDetailed=?, tourCost=?, tourSlots=?, tourType=?, tourPicture=?, tourDate=? WHERE tourID=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, tourName);
			pstmt.setString(2, tourDesc);
			pstmt.setString(3, tourDetailed);
			pstmt.setFloat(4, tourCost);
			pstmt.setInt(5, tourSlots);
			pstmt.setInt(6, tourType);
			pstmt.setString(7, tourImg);
			pstmt.setString(8, tourDate);
			pstmt.setInt(9, tourID);
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
	
	public ArrayList<Tour> getToursByUser(String email) {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM `tour-users` tu, tour t WHERE tu.useremail=? AND t.tourID = tu.tourid";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourID(rs.getInt("tourID"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourSlots(rs.getInt("tourSlots"));
				tour.setTourType(rs.getInt("tourType"));
				tour.setTourImg(rs.getString("tourPicture"));
				tour.setDate(rs.getString("date"));
				tour.setTourDate(rs.getString("tourDate"));
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
	
	public ArrayList<Tour> getToursBySales() {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "select t.tourID, t.tourName, t.tourDescription, t.tourDetailed, t.tourCost, t.tourSlots, t.tourType, t.tourPicture, t.tourDate, count(tu.useremail) as 'filled' from tour t left join `tour-users` tu on t.tourID = tu.tourid group by t.tourID, t.tourName, t.tourDescription, t.tourDetailed, t.tourCost, t.tourSlots, t.tourType, t.tourPicture order by count(tu.useremail) desc";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Tour tour = new Tour();
				tour.setTourID(rs.getInt("tourID"));
				tour.setTourName(rs.getString("tourName"));
				tour.setTourDescription(rs.getString("tourDescription"));
				tour.setTourDeatiled(rs.getString("tourDetailed"));
				tour.setTourCost(rs.getFloat("tourCost"));
				tour.setTourSlots(rs.getInt("tourSlots"));
				tour.setTourType(rs.getInt("tourType"));
				tour.setTourImg(rs.getString("tourPicture"));
				tour.setSlotsFilled(rs.getInt("filled"));
				tour.setTourDate(rs.getString("tourDate"));
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
}

