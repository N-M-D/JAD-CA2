<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
<style>
h1 {
	border-bottom: 2px solid whitesmoke;
	margin: .5em 1em;
}

.main {
	width: 60%;
	margin: 1em auto;
	background: #222;
	padding: .5em;
	color: white;
	justify-content: center;
	align-items: center;
	text-align: center;
	border-radius: 15px;
	box-shadow: 10px 10px 15px gray
}

img {
	height: auto;
	width: 100%;
	border-radius: 1em;
	object-fit: cover;
}

.pics {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	gap: 1rem
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="main">
	<h1>SPTravel</h1>
	<h2>About Us</h2>
	<p>We are a small start up company that aims to provide cheap and fun tours for everyone. From relaxing boat tours to exhilarating roller coaster rides, there is always something for everyone</p>
	<h2>Why Choose Us?</h2>
	<div class="pics">
		<div id="money">
			<a href="https://unsplash.com/@alschim?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText"><img src="<%=request.getContextPath()%>/CA2/img/money.jpg"></a>
			<h2>Affordable</h2>
			<p>All our tours are reasonably priced, and we frequently give away offers and discounts for our loyal customers.</p>
		</div>
		<div id="food">
			<img src="<%=request.getContextPath()%>/CA2/img/food.jpg">
			<h2>Food</h2>
			<p>We provide everyone with food during our tours. Our meals are chosen only from the highest quality vendors, and are sure to give everyone the finest taste of our culture.</p>
		</div>
		<div id="safety">
			<img src="<%=request.getContextPath()%>/CA2/img/safety.jpg">
			<h2>
			Safety
			</h2>
			<p>All our tour guides are first-aid trained, and all our attractions have go through constant and vigorous testing to ensure the safety of all our guests.</p>
		</div>
	</div><!-- END PICS -->
</div><!-- END MAIN -->
<%@ include file="footer.jsp" %>
</body>
</html>