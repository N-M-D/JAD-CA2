package categoriesDBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDB {
	public ArrayList<Category> getCategories(){
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			String sqlStr = "SELECT * FROM categories";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categoryList.add(category);
			}
			// Step 7: Close connection
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return categoryList;
	}
}
