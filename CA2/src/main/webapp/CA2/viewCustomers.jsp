<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="userDBAccess.*" %>
<%
	ArrayList<User> userList = new ArrayList<User>();
	userDB udb = new userDB();
	userList = udb.getAllCustomers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SPTravel</title>
</head>
<body>
<h1>View All Customers</h1>
<%
	for(int i = 0; i < userList.size(); i++){
		out.print("<div>");
		out.print("<h1>" + userList.get(i).getUsername() + "</h1><br>");
		out.print("<h4>" + userList.get(i).getEmail() + "</h4><br>");
		out.print("<p>" + userList.get(i).getRegion() + "</p></div>");
	}
%>
</body>
</html>