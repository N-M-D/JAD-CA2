<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.sql.*" %>
<%
String email = request.getParameter("email");
String password = request.getParameter("password");
boolean verified = false;

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
	String sqlStr = "SELECT * FROM users WHERE email=?";
	PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	pstmt.setString(1, email);
	//pstmt.setString(2, password);
	ResultSet rs = pstmt.executeQuery();
	// Step 6: Process Result
	while (rs.next()) {
		String userPassword = rs.getString("password");
		String username = rs.getString("username");
		String role = rs.getString("role");
		
		//Check if password matches
		if(password.equals(userPassword)){
			session.setAttribute("email", email);
			session.setAttribute("username", username);
			session.setAttribute("role", role);
			response.sendRedirect("index.jsp");
		}else{
			response.sendRedirect("login.jsp?errCode=wrongPass");
		}
		verified = true;
	}
	
	//Redirect to login.jsp if email does not exist
	if(!verified){
		out.print("Cannot find user with email " + email);
		response.sendRedirect("login.jsp?errCode=invalidEmail");
	}
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
} catch (Exception e) {
		System.out.println("Error :" + e);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>