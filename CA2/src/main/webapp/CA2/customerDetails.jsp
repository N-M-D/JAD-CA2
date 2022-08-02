<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="userDBAccess.*" %>
<%
	String email = request.getParameter("email");
	User user = new User();
	userDB udb = new userDB();
	user = udb.readUser(email);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SP Travel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Customer Details</h1>
<%=email %>
</body>
</html>