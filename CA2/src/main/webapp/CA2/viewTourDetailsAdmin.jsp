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
		response.sendRedirect(request.getContextPath() + "/CA2/viewTourSales.jsp");
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
	
	userDB udb = new userDB();
	ArrayList<User> userList = new ArrayList<User>();
	userList = udb.getCustomersByTour(tourID);
	
	int catid = tour.getTourType();
	Category cat = new Category();
	CategoryDB catdb = new CategoryDB();
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
}

.tourDetails h2 {
  border-bottom: 1px solid white
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

</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>View Tour Details Admin</h1>
<div class="main">
	<div class="tourPic">
		<img class="tourImg" src="<%=imgLink%>"/>
	</div>
	<div class="tourDetails">
		<h2><%=tour.getTourName() %></h2>
		<p>$<%=tour.getTourCost() %></p>
		<p>Category: <%=cat.getName() %></p>
		<p><%=tour.getTourDeatiled() %></p>
		<p>Capacity: <%=userList.size()%>/<%=tour.getTourSlots() %></p>
	</div>
	<div class="usersList">
		<h1>Users</h1>
		<%
		for(int i = 0; i < userList.size(); i++){
			User user = new User();
			user = userList.get(i);
			String pfp = user.getPfp();
			if(pfp == null){
				pfp = request.getContextPath() + "/CA2/img/default_pfp.jpg";
			}
			
			out.print("<a class='user' href='" + request.getContextPath() + "/CA2/viewCustomerDetails.jsp?email=" + user.getEmail() + "'>");
			out.print("<img class='userpfp' src='" + pfp + "'/>");
			out.print("<h2>" + user.getUsername() + "</h2>");
			out.print("</a>");
		}	
			%>
		
	</div>
</div>
</body>
</html>