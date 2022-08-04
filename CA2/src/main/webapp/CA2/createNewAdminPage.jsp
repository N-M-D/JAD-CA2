<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	String errCode = request.getParameter("errCode");
	String userRole = (String) session.getAttribute("role");
	boolean duplicate = false;
	if(!userRole.equals("admin")){
		response.sendRedirect("index.jsp");
	}
	if(errCode != null){
		if(errCode.equals("dup")){
			duplicate = true;
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
	.loginLink {
		color: yellow;
		text-decoration: none;
		background-image: linear-gradient(90deg, red, blue);
		background-repeat: no-repeat;
		background-position: bottom left;
		background-size: 0% 3px;
		transition: background-size 500ms ease-in-out;
		font-weight: bold;
	}
	.loginLink:hover {
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
<h1>Create Admin User</h1>
<%
	if(duplicate){
		out.print("<div class='errMessage'><p>This email is already in use!</p></div>");
	}
%>
<form action="createAdminUser.jsp">
	<label for="email">Email</label>
	<div>
		<input id="email" type="text" name="email" required><br>
	</div>
	<label for="username">Username</label>
	<div>
		<input id="username" type="text" name="username" required><br>
	</div>
	<label for="password">Password</label>
	<div>
		<input id="passowrd" type="password" name="password" required><br>
	</div>
	<div class="registerButton">
		<button type="submit">Register</button>
	</div>
</form>
<p>Already have an account with us? <a href="login.jsp" class="loginLink">Login</a>
</div><!-- End Main -->
</div><!-- End Wrapper -->
<%@ include file="footer.jsp" %>
</body>
</html>