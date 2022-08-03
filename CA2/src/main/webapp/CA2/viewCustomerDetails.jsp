<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="userDBAccess.*" %>
<%@ page import="toursDBAccess.*" %>
<%@ page import="java.util.*" %>
<%
	String email = request.getParameter("email");
	if(email == null){
		String url = request.getContextPath() + "/CA2/viewCustomers.jsp";
		response.sendRedirect(url);
	}
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
<style>


.main{
	display: grid;
	grid-template-columns: 1fr 3fr;
	width: 60vw;
	margin: 1em auto;
	background-color: #222;
	color: white;
}

.pfp {
	justify-content: center;
	display: flex; 
}

.userpfp{
	max-height: 12em;
	margin-left: auto;
	margin-right: auto;
}

.main div {
	border: 1px solid black
}

.userTransactions {
	grid-column: 1 / 2 span
}


.userDetails {
	padding: 1em;
}

div h1 {
	border-bottom: 1px solid white
}

.tourPic {
	max-height: 7em;
}

.userTour {
	display: flex;
	justify-content:space-between;
}

.tourDetails {
	display: grid;
	margin-left: 1em;
}

.tourLeft {
	display: flex;
}

.date {
	margin-right: 1em;
	text-align: center;
}

.userControl {
	display: grid;
	grid-template-columns: 1fr 1fr;
}

.userControlRight {
	display: flex;
	align-items: end;
}

.buttonGroup {
	margin-left: auto;
}

.buttonGroup input {
	padding: 0.5em 1em;
	margin-inline: 1em;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Customer Details</h1>
<div class="main">
<div class="pfp">
<%
	String pfp = user.getPfp();
	if(pfp == null){
		pfp = request.getContextPath() + "/CA2/img/default_pfp.jpg";
	}
	out.print("<img class='userpfp' src='" + pfp + "'/>");
%>
</div>
<div class="userDetails">
<%
	out.print("<h1>" + user.getUsername() + "</h1>");
	out.print("<div class='userControl'>");
	out.print("<div class='userControlLeft'><p>" + user.getEmail() + "</p>");
	out.print("<p>" + user.getRegion() + "</p>");
	out.print("<form action='" + request.getContextPath() + "/UpdateCustomer?email=" + email + "' method='POST'><input name='points' type='number' value='" + user.getPoints() + "'></div>");
	out.print("<div class='userControlRight'><div class='buttonGroup'><input type='submit' name='button' value='edit'><input type='submit' name='button' value='delete'></div></div></div>");
%>
</div>
<div class='userTransactions'>
<h1>Transaction History</h1>
<%
	TourDB tourdb = new TourDB();
	ArrayList<Tour> tourList = new ArrayList<Tour>();
	tourList = tourdb.getToursByUser(email);
	for(int i = 0; i < tourList.size(); i++){
		Tour tour = new Tour();
		tour = tourList.get(i);
		String tourPic = tour.getTourImg();
		if (tourPic == null){
			tourPic = request.getContextPath() + "/CA2/img/default_tour_img.jpg";
		}
		out.print("<div class='userTour'>");
		out.print("<div class='tourLeft'><img src='" + tourPic + "' class='tourPic'>");
		out.print("<div class='tourDetails'><h2>" + tour.getTourName() + "</h2>");
		out.print("<p>&#128181;$" + tour.getTourCost() + "</p></div></div>");
		out.print("<div class='date'><h4>Date</h4>" + tour.getDate() + "</div></div>");
	}
%>
</div>
</div>
</body>
</html>