package promoCodesDBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PromoCodeDB {
	public PromoCode getPromoCode(int id) {
		PromoCode promoCode = new PromoCode();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM promo_codes WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				promoCode.setId(rs.getInt("id"));
				promoCode.setCode(rs.getString("code"));
				promoCode.setDiscount(rs.getFloat("discount"));
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return promoCode;
	}
	
	public PromoCode claimPromoCode(String code) {
		PromoCode promoCode = new PromoCode();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM promo_codes WHERE code=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				promoCode.setId(rs.getInt("id"));
				promoCode.setCode(rs.getString("code"));
				promoCode.setDiscount(rs.getFloat("discount"));
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return promoCode;
	}
	
	public int createPromoCode(String code, float discount) {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "INSERT INTO promo_codes (code, discount) VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, code);
			pstmt.setFloat(2, discount);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return rows;
	}
	
	public int updatePromoCode(String code, float discount) {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "UPDATE promo_codes SET (code, discount) VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, code);
			pstmt.setFloat(2, discount);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return rows;
	}
	
	public int deletePromoCode(int id) {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "DELETE FROM promo_codes WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, id);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return rows;
	}
}
