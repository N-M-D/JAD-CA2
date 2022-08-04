<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="toursDBAccess.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Tour Sales</h1>
<div class="main">
<%
	TourDB tdb = new TourDB();
	ArrayList<Tour> toursList = new ArrayList<Tour>();
	toursList = tdb.getToursBySales();
	for(int i = 0; i < toursList.size(); i++){
		Tour tour = new Tour();
		tour = toursList.get(i);
		out.print("<h2>" + tour.getTourName() + "</h2>");
		out.print("<p>" + tour.getTourDescription() + "</p>");
		out.print("<p>Slots Filled:" + tour.getSlotsFilled() + "/" + tour.getTourSlots());
	}
%>
</div>
</body>
</html>