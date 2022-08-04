<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="userDBAccess.*" %>
<%@ page import="regionsDBAccess.*" %>
<%
	String role = (String)session.getAttribute("role");
	if(role != null){
		if(role != "admin"){
			String url = request.getContextPath() + "/CA2/index.jsp";
			response.sendRedirect(url);
		}
	}
%>
<%
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Region> regionList = new ArrayList<Region>();
	userDB udb = new userDB();
	regionDB rdb = new regionDB();
	String region = request.getParameter("region");
	regionList = rdb.getRegions();
	if(region != null){
		userList = udb.getCustomersByRegion(region);
	}else{
		userList = udb.getAllCustomers();
	}
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
	 box-shadow: 0px 0px 10px white;
}

.userDetails{
	list-style: none;
}

.location:before {
	content: '\1F3E1'; 
	margin-left: -20px; 
	margin-right: 10px; 
}

.userCardLink {
	text-decoration: none;
}

.filter {
	display: flex;
	width: 100%;
	grid-column: 1 / span 2;
	justify-content: space-around;
}

.regionSelector {
	background-color: white;
	padding: 1em;
	margin: 1em;
	
}

</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View All Customers</h1>
<div class="main">
<div class='filter'>
	<a href='<%=request.getContextPath()%>/CA2/viewCustomers.jsp'><div class='regionSelector'>All</div></a>
<%
	for(int i = 0; i < regionList.size(); i++){
		out.print("<a href='" + request.getContextPath() + "/CA2/viewCustomers.jsp?region=" + regionList.get(i).getName() +"'><div class='regionSelector'>" + regionList.get(i).getName() + "</div></a>");
	}
%>
</div>
<%
	for(int i = 0; i < userList.size(); i++){
		out.print("<a href='" + request.getContextPath()+"/CA2/viewCustomerDetails.jsp?email=" + userList.get(i).getEmail() + "' class='userCardLink'><div class='userCard'>");
		out.print("<h1>" + userList.get(i).getUsername() + "</h1>");
		out.print("<ul class='userDetails'>");
		out.print("<li class='email'>" + userList.get(i).getEmail() + "</li>");
		out.print("<li class='location'>" + userList.get(i).getRegion() + "</li></ul></div></a>");
	}
%>
</div>

</body>
</html>