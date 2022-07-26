<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	//check if user is already logged in
	String email = (String) session.getAttribute("email");
	if(email != null){
		response.sendRedirect("./user.jsp");
	}
	boolean wrongPass = false;
	boolean wrongEmail = false;
	boolean loginRequired = false;
	String errCode = request.getParameter("errCode");
	if(errCode != null){
		if(errCode.equals("wrongPass")){
			//If password entered is wrong
			wrongPass = true;
		}else if(errCode.equals("invalidEmail")){
			//If email does not exist
			wrongEmail = true;
		}else if(errCode.equals("loginRequired")){
			loginRequired = true;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SPTravel</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
<link rel="icon" type="image/jpg" href="https://dbcsingapore.org/wp-content/uploads/2021/12/SP.jpg"><!-- Website Icon -->
<style>
	#main {
		color: white;
		margin: 0 auto;
		margin-top: 2em;
		padding: 1rem;
		width: 60vw;
		background-color: #222;
		max-height: 75vh;
		text-align: center;
		border-width: 4px solid white;
	}
	form {
		display: grid;
		grid-template-columns: 1fr;
		align-items: center;
	}
	input {
		padding: 10px;
		width: 75%;
		margin: 15px
	}
	label {
		color: white;
		font-size: larger;
		margin: 5px;
	}
	button {
		margin: 5px;
		padding: .5rem 1.25rem;
		cursor: pointer;
		color: black;
		font-weight: bold;
		font-size: large;
		background-color: white;
		border: .25em solid green;
		border-radius: 15px;
	  	transition: 250ms ease-in-out;
	}
	button:hover {
		color: white;
		background-color: green;
		transform: translateY(-5px);
	}
	.registerLink {
		color: yellow;
		text-decoration: none;
		background-image: linear-gradient(90deg, red, blue);
		background-repeat: no-repeat;
		background-position: bottom left;
		background-size: 0% 3px;
		transition: background-size 500ms ease-in-out;
		font-weight: bold;
	}
	.registerLink:hover {
		background-size: 100% 3px;
	}
	h1{
		border-bottom: 2px solid white;
		margin-left: 1em;
		margin-right: 1em
	}
	.errMessage {
		background-color: rgba(255, 0, 0, 0.5);
		padding: .01em;
		margin-left: 3em;
		margin-right: 3em;
		margin-bottom: 1em;
		border-radius: 1em
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="mainWrapper">
<div id="main">
	<h1>Login</h1>
	<%
		if(wrongEmail){
			out.print("<div class='errMessage'><p>Please enter a valid email!</p></div>");
		}else if(wrongPass){
			out.print("<div class='errMessage'><p>Incorrect Password!</p></div>");
		}else if(loginRequired){
			out.print("<div class='errMessage'><p>You need to login to access that page!</p></div>");
		}
	%>
	<form action="<%=request.getContextPath()%>/userLogin" method="post">
		<div>
			<label for="email">Email</label>
		</div>
		<div>
			<input id="email" type="text" name="email" required><br>
		</div>
		<div>
			<label for="password">Password</label>
		</div>
		<div>
			<input id="password" type="password" name="password" required><br>
		</div>
		<div class="submitButton">
			<button type="submit" id="login">Login</button>
		</div>
	</form>
	<p>Don't have an account? <a href="./register.jsp" class="registerLink">Register</a> now!</p>
</div><!-- End Main -->
</div><!-- End Wrapper -->
<%@ include file="footer.jsp" %>
</body>
</html>