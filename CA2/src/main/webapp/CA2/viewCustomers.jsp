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
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
<style>

.main {
	display: grid;
	grid-template-columns: 1fr 1fr;
	align-items: center;
	gap: 1em;
	justify-content: center;
	background-color: #222;
	width: 60vw;
	margin: 0 auto;
	padding: 1em;
}

.userCard {
	background-color: #222;
	color: white;
	padding: 0.5em 1.5em;
	transition: 100ms ease-in-out;
}

.userCard h1 {
	border-bottom: 1px solid white;
	}

.userCard:hover {
	 box-shadow: 0px 0px 10px white
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View All Customers</h1>
<div class="main">
<%
	for(int i = 0; i < userList.size(); i++){
		out.print("<a href='" + request.getContextPath()+"/CA2/customerDetails?email=" + userList.get(i).getEmail() + "'><div class='userCard'>");
		out.print("<h1>" + userList.get(i).getUsername() + "</h1>");
		out.print("<p>" + userList.get(i).getEmail() + "</p><br>");
		out.print("<p>" + userList.get(i).getRegion() + "</p></div></a>");
	}
%>
</div>

</body>
</html>