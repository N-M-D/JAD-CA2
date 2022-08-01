<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="toursDBAccess.*" %>
<%@ page import="categoriesDBAccess.*" %>

<%
	String cat = request.getParameter("category");
	boolean catFilter = false;
	ArrayList<Tour> tourList = new ArrayList<Tour>();
	ArrayList<Category> catList = new ArrayList<Category>();
	CategoryDB catdb = new CategoryDB();
	TourDB tdb = new TourDB();
	if(cat != null){
		catList = catdb.getCategories();
		catFilter = true;
		System.out.println("cat");
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
<link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main">
<h1>Tours</h1>
<%
	if(catFilter){
		for(int i = 0; i < catList.size(); i++){
			out.print("<div class='category'>");
			int catid = catList.get(i).getId();
			String name = catList.get(i).getName();
			out.print("<h2>" + name + "</h2>");
			tourList = tdb.getToursByCategory(catid);
			for(int c = 0; c < tourList.size(); c++){
				out.print("<br>" + tourList.get(c).getTourName());
			}
		}//Loop through each category
	}else{
		System.out.println("tours: " + tourList.size());
		for(int i = 0; i < tourList.size(); i++){
			out.print("<h2>" + tourList.get(i).getTourName() + "</h2><br>");
		}
	}
 %>
</div>
</body>
</html>