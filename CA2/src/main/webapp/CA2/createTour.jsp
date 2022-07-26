<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.sql.*" %>
<%@ page import =  "java.io.*" %>  
<%
String tourName = request.getParameter("tourName");
String tourCost = request.getParameter("tourCost");
String tourType = request.getParameter("tourType");
String tourDescription = request.getParameter("tourDescription");



InputStream tourImage = new FileInputStream(request.getParameter("tourImage"));
String role = (String) session.getAttribute("role");
if(role == null){
	response.sendRedirect("login.jsp");
}else if(!role.equals("admin")){
	response.sendRedirect("index.jsp");
}else{
	try {
		// Step1: Load JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");
		// Step 2: Define Connection URL
		String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304CTy&serverTimezone=UTC";
		// Step 3: Establish connection to URL
		Connection conn = DriverManager.getConnection(connURL);
		// Step 4: Create Statement object
		Statement stmt = conn.createStatement();
		// Step 5: Execute SQL Command
		//String sqlStr = "SELECT * FROM member WHERE name=? and password=?";
		//out.print(sqlStr);
		String sqlStr = "INSERT INTO tour (tourName, tourType, tourDescription, tourCost) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
		pstmt.setString(1, tourName);
		pstmt.setString(2, tourType);
		pstmt.setString(3, tourDescription);
		pstmt.setString(4, tourCost);
		pstmt.setBlob(5, tourImage);
		//pstmt.setString(2, password);
		boolean success = pstmt.execute();
		// Step 6: Process Result
		if(!success){
			out.print("Insert Successful");
		}else{
			out.print("Error with inserting");
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
		response.sendRedirect("tour.jsp?errCode=dup");
		out.println("Error :");
	}
	catch (Exception e) {
		out.println("Error :" + e);
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