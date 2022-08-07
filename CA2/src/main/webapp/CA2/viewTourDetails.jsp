<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="toursDBAccess.*" %>
<%@ page import="userDBAccess.*" %>
<%@ page import="categoriesDBAccess.*" %>
<%@ page import="java.util.*" %>
<%
	String tourid = request.getParameter("tourID");
	int tourID = 0;
	if(tourid == null){
		response.sendRedirect(request.getContextPath() + "/CA2/tours.jsp");
	}else{
		tourID = Integer.parseInt(tourid);
	}
	Tour tour = new Tour();
	TourDB tdb = new TourDB();
	tour = tdb.getTour(tourID);
	String imgLink = request.getContextPath() + "/CA2/img/default_tour_img.jpg";
	if(tour.getTourImg() != null){
		imgLink = tour.getTourImg();
	}
	
	int catid = tour.getTourType();
	CategoryDB catdb = new CategoryDB();
	Category cat = new Category();
	cat = catdb.getCategory(catid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SP Travel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
<style>

.main {
  width: 60vw;
  margin: 0 auto;
  background-color: #222;
  padding: 1em;
  color: white;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.tourImg {
  width: 100%;
}

.tourDetails {
  padding: 0 1em ;
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 0.5em;
}

.tourDetails h2 {
  border-bottom: 1px solid white
}

.buttonGroup {
  display: flex;
  justify-content: space-around;
  grid-column: 1 / 2 span;
  margin: 0.5em;
  padding: 0.5em;
}

.buttonGroup button {
  background-color: darkgreen;
  padding: 0.5em 1em;
}

.usersList {
  grid-column: 1 / 2 span;
}

.user {
  display: flex;
  margin: 1em;
  padding: 1em;
  border-bottom: 1px solid white;
  text-decoration: none;
  color: white;
}

.userpfp{
  max-width: 5em;
  margin-right: 1em;
  border-radius: 100vmax;
}

.user:hover {
  box-shadow: 0px 0px 1em black;
}

.tourDetails div {
  display: flex;
  border-bottom: 1px solid #333
}

label {
  margin-top: auto;
  margin-bottom: auto;
  font-weight: bold;
}




</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Tour Details</h1>
<div class="main">
	<div class="tourPic">
		<img class="tourImg" src="<%=imgLink%>"/>
	</div>
	<div class="tourDetails">
		<div>
			<label>Tour Name: </label>
		</div>
		<div>
			<p><%=tour.getTourName() %></p>
		</div>	
			
		<div>
			<label>Tour Price($): </label>
		</div>
		<div>
			<p><%=tour.getTourCost() %></p>
		</div>
		<div>
			<label>Tour Description: </label>
		</div>
		<div>
			<p><%=tour.getTourDescription() %></p>
		</div>
		<div>
			<label>Tour Detailed: </label>
		</div>
		<div>
			<p><%=tour.getTourDeatiled() %></p>
		</div>
		<div>
			<label>Tour Slots: </label>
		</div>
		<div>
			<p><%=tour.getTourSlots()%></p>
		</div>
		<div>
			<label>Tour Category: </label>
		</div>
		<div>
			<p><%=cat.getName() %></p>
		</div>
		<div>
			<label>Tour Date</label>
		</div>
		<div>
			<p><%= tour.getTourDate() %></p>
		</div>
		<div class="buttonGroup">
		<form action="<%=request.getContextPath()%>/joinTour?tourID=<%=tourID %>" method="POST">
			<button type="submit">Join</button>
		</form>
			
		</div>
	</div>
</div>
</body>
</html>