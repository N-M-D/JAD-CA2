<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.sql.*" %>
<%
	String email = (String) session.getAttribute("email");
	if(email == null){
		response.sendRedirect("index.jsp");
	}else{
		session.invalidate();
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
			String sqlStr = "DELETE FROM users WHERE email=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			//pstmt.setString(2, password);
			boolean success = !pstmt.execute();
			// Step 6: Process Result
			if(success){
				out.print("<h1>Delete Successful!</h1><br><a href='index.jsp'>Return to Homepage</a>");
				//response.sendRedirect("index.jsp");
			}else{
				out.print("Error with update");
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
		} 
		catch(SQLIntegrityConstraintViolationException e){
			response.sendRedirect("register.jsp?errCode=dup");
		}
		catch (Exception e) {
			System.out.println("Error :" + e);
		}
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