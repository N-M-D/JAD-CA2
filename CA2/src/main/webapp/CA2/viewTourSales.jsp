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
<style>
.main{
	background-color: #222;
	display: grid;
	grid-template-columns: 1fr 1fr;
	width: 60vw;
	margin: 0 auto;
	margin-bottom: 10em;
}

.tourCard {
	background-color: #222;
	color: white;
	margin: 1em;
	padding: 0.5em;
	transition: 150ms ease-in-out;
	text-decoration: none;
}

.tourCard:hover{
	box-shadow: 0 0 1em black;
}

.tourPic{
	max-width: 100%;
}

.newTour {
	grid-column: 1 / 2 span;
	text-align: center;
	text-decoration: none;
	padding: 1em;
	background-color: white;
	margin: 1em;
	color: black;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Tour Sales</h1>
<div class="main">
<a href="<%=request.getContextPath()%>/CA2/createTourPage.jsp" class="newTour">Create New Tour</a>
<%
	TourDB tdb = new TourDB();
	ArrayList<Tour> toursList = new ArrayList<Tour>();
	toursList = tdb.getToursBySales();
	for(int i = 0; i < toursList.size(); i++){
		Tour tour = new Tour();
		tour = toursList.get(i);
		String tourPic = tour.getTourImg();
		if (tourPic == null || tourPic.equals("")){
			tourPic = request.getContextPath() + "/CA2/img/default_tour_img.jpg";
		}
		out.print("<a class='tourCard' href='" + request.getContextPath() +"/CA2/viewTourDetailsAdmin.jsp?tourID=" + tour.getTourID() + "'>");
		out.print("<img src='" + tourPic + "' class='tourPic'>");
		out.print("<h2>" + tour.getTourName() + "</h2>");
		out.print("<p>" + tour.getTourDescription() + "</p>");
		out.print("<p>$" + tour.getTourCost() + "</p>");
		out.print("<p>Slots Filled:" + tour.getSlotsFilled() + "/" + tour.getTourSlots() + "</p> ");
		out.print("</a>");
	}
%>
</div>
</body>
</html>