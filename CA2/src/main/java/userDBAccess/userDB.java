package userDBAccess;
import java.sql.*;

public class userDB {

	public User verifyUser(String email, String password) throws SQLException {
		User user = new User();
		Connection conn = null;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			String sqlStr = "SELECT * FROM users WHERE email = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			// Step 5: Execute SQL Command
			ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			String username = null;
			String role = null;
			String mail = null;
			while(rs.next()) {
				mail = rs.getString("email");
				username = rs.getString("username");
				role = rs.getString("role");
			}
			user.setEmail(mail);
			user.setUsername(username);
			user.setRole(role);
		}catch (Exception e) {
			System.out.println("Error :" + e);
		}finally {
			// Step 7: Close connection
			conn.close();
		}
		return user;
	}//End verifyUser
	
	//Create New User
	public int createUser(String email, String username, String password, String role) throws SQLException {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			//out.print(sqlStr);
			String sqlStr = "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, role);
			//pstmt.setString(2, password);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		} 
		catch(SQLIntegrityConstraintViolationException e){
			rows = -1;
			//Returns -1 if email already exists
		}
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
		return rows;
	}//End Create User
	
	//Read User
	public User readUser(String email) throws SQLException{
		User user = new User();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			//out.print(sqlStr);
			String sqlStr = "SELECT * FROM users WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			//pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String role = rs.getString("role");
				String password = rs.getString("password");
				user.setEmail(email);
				user.setUsername(username);
				user.setPasword(password);
				user.setRole(role);
			}
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return user;
	}//End Read User
	
	//Update User
	public int updateUser(String email, String username, String password) throws SQLException {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "UPDATE USERS SET username = ?, password = ? WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);	
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			//pstmt.setString(2, password);
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return rows;
	}//End Update User
	
	//Delete User
	public int deleteUser(String email) throws SQLException {
		int rows = 0;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "DELETE FROM users WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			//pstmt.setString(2, password);
			boolean success = !pstmt.execute();
			// Step 6: Process Result
			if(success){
				System.out.println("Delete Successful");
				//response.sendRedirect("index.jsp");
			}else{
				System.out.println("Error with update");
			}
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return rows;
	}//End Delete User
}//End Class
