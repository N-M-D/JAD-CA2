<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="toursDBAccess.*" %>
<%@ page import="categoriesDBAccess.*" %>

<%
	String sort = request.getParameter("sort");
	boolean catFilter = false;
	ArrayList<Tour> tourList = new ArrayList<Tour>();
	ArrayList<Category> catList = new ArrayList<Category>();
	CategoryDB catdb = new CategoryDB();
	TourDB tdb = new TourDB();
	if(sort != null){
		if(sort.equals("cat")){
			catList = catdb.getCategories();
			catFilter = true;
			System.out.println("cat");
		}else if(sort.equals("price")){
			tourList = tdb.getToursByPrice();
		}
	}else{
		tourList = tdb.getTours();
		System.out.println("tours: " + tourList.size());
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SPTravel</title>
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
.filter {
	grid-column: 1 / 2 span;
}
.category {
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-column: 1 / 2 span;
}
.category h2 {
	grid-column: 1 / 2 span;
	background-color: lime;
}
</style>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>Tours</h1>
<div class="main">
<div class="filter">
	<h4>Filter By:</h4>
	<a href="<%=request.getContextPath()%>/CA2/tours.jsp?sort=cat">Category</a>
	<a href="<%=request.getContextPath()%>/CA2/tours.jsp?sort=price">Price</a>
	<a href="<%=request.getContextPath()%>/CA2/tours.jsp">None</a>
</div>
<%
	if(catFilter){
		for(int i = 0; i < catList.size(); i++){
			out.print("<div class='category'>");
			int catid = catList.get(i).getId();
			String name = catList.get(i).getName();
			out.print("<h2 class='catName'>" + name + "</h2>");
			tourList = tdb.getToursByCategory(catid);
			for(int c = 0; c < tourList.size(); c++){
				Tour tour = new Tour();
				tour = tourList.get(c);
				String tourPic = tour.getTourImg();
				if (tourPic == null || tourPic.equals("")){
					tourPic = request.getContextPath() + "/CA2/img/default_tour_img.jpg";
				}
				out.print("<a class='tourCard' href='" + request.getContextPath() +"/CA2/viewTourDetailsAdmin.jsp?tourID=" + tour.getTourID() + "'>");
				out.print("<img src='" + tourPic + "' class='tourPic'>");
				out.print("<h2>" + tour.getTourName() + "</h2>");
				out.print("<p>" + tour.getTourDescription() + "</p>");
				out.print("<p>$" + tour.getTourCost() + "</p>");
				out.print("</a>");
			}
			out.print("</div>");
		}//Loop through each category
	}else{
		System.out.println("tours: " + tourList.size());
		for(int i = 0; i < tourList.size(); i++){
			Tour tour = new Tour();
			tour = tourList.get(i);
			String tourPic = tour.getTourImg();
			if (tourPic == null || tourPic.equals("")){
				tourPic = request.getContextPath() + "/CA2/img/default_tour_img.jpg";
			}
			out.print("<a class='tourCard' href='" + request.getContextPath() +"/CA2/viewTourDetailsAdmin.jsp?tourID=" + tour.getTourID() + "'>");
			out.print("<img src='" + tourPic + "' class='tourPic'>");
			out.print("<h2>" + tour.getTourName() + "</h2>");
			out.print("<p>" + tour.getTourDescription() + "</p>");
			out.print("<p>$" + tour.getTourCost() + "</p>");
			out.print("</a>");
		}
	}
 %>
</div>
</body>
</html>