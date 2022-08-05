package userDBAccess;
import java.sql.*;
import java.util.ArrayList;

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
	public int createUser(String email, String username, String password, String role, String region, String pfp) throws SQLException {
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
			String sqlStr = "INSERT INTO users (email, username, password, role, region, pfp) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, role);
			pstmt.setString(5, region);
			pstmt.setString(6, pfp);
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
				String region = rs.getString("region");
				String pfp = rs.getString("pfp");
				int points = rs.getInt("points");
				user.setEmail(email);
				user.setUsername(username);
				user.setPasword(password);
				user.setRole(role);
				user.setRegion(region);
				user.setPfp(pfp);
				user.setPoints(points);
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
	public int updateUser(String email, String username, String password, String region, String pfp) throws SQLException {
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
			String sqlStr = "UPDATE USERS SET username = ?, password = ?, region = ? WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);	
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, region);
			pstmt.setString(5, pfp);
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
			rows = pstmt.executeUpdate();
			// Step 6: Process Result
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return rows;
	}//End Delete User
	
	public ArrayList<User> getAllCustomers() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "SELECT * FROM users WHERE role='guest'";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setRegion(rs.getString("region"));
				user.setPfp(rs.getString("pfp"));
				userList.add(user);
			}
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return userList;
	}
	
//----------Filter Users--------------------------
	
	public ArrayList<User> getCustomersByRegion(String region) throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "SELECT * FROM users WHERE region=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, region);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setRegion(rs.getString("region"));
				user.setPfp(rs.getString("pfp"));
				userList.add(user);
			}
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return userList;
	}
	
	public ArrayList<User> getCustomersByTour(int tourid) throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "SELECT * FROM `tour-users` tu INNER JOIN users u ON tu.useremail = u.email WHERE tourid=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, tourid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setRegion(rs.getString("region"));
				user.setPfp(rs.getString("pfp"));
				user.setPoints(rs.getInt("points"));
				userList.add(user);
			}
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return userList;
	}
	
	public boolean updatePoints(String email, int points) throws SQLException {
		boolean success = false;
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "UPDATE users SET points = ? WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, points);
			pstmt.setString(2, email);
			success = pstmt.execute();
			// Step 7: Close connection
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
}//End Class
