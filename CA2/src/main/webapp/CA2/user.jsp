<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String email = (String) session.getAttribute("email");
	String username = (String) session.getAttribute("username");
	String role = (String) session.getAttribute("role");
	if(email == null){
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CA2/style.css">
<link rel="icon" type="image/jpg" href="https://dbcsingapore.org/wp-content/uploads/2021/12/SP.jpg"><!-- Website Icon -->
<style>
#main {
	color: white;
	margin: 0 auto;
	margin-top: 2em;
	padding: 1em;
	padding-bottom: 2em;
	width: 60vw;
	background-color: #222;
	max-height: 75vh;
	text-align: center;
	border-width: 4px solid white;
}
form {
	display: grid;
	grid-template-columns: 1fr 5fr;
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
.label {
	text-align: left;
	margin-left: 3em;
}
h1{
	border-bottom: 2px solid white;
	margin-left: 1em;
	margin-right: 1em
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
.submitButton {
	grid-column: 1 / 3
}
#logOut, #deleteAcc {
	border-color: red;
}
#logOut:hover, #deleteAcc:hover {
	background-color: red;
}

.adminControls {
	list-style: none;
}

.adminControls li {
	margin: 1em
}

.adminControls > li a {
	color: whitesmoke;
	text-decoration: none;
	background-image: linear-gradient(90deg, red, blue);
	background-repeat: no-repeat;
	background-position: bottom left;
	background-size: 0% 3px;
	transition: background-size 500ms ease-in-out;
	font-weight: bolder;
}

.adminControls > li a:hover {
	background-size: 100% 3px;
}
	

</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div id="main">
<div id="title">
	<h1>User</h1>
</div>
<form action="updateUser.jsp">
	<div class="label">
		<label for="email">Email</label>
	</div>
	<div>
		<input id='email' type='text' name="email" value='<%=email%>' readonly><br>
	</div>
	<div class="label">
		<label for="username">Username</label>
	</div>
	<div>
		<input id='username' type='text' name='username' value='<%=username%>'><br>
	</div>
	<div class="label">
		<label for="password">New Password</label>
	</div>
	<div>
		<input id="password" type="password" name="password"><br>
	</div>
	<div class="submitButton">
		<button type="submit">Edit</button>
	</div>
</form>
<a href="logOut.jsp"><button id="logOut">Log Out</button></a>
<button id="deleteAcc">Delete Account</button>
<%
if(role != null){
if(role.equals("admin")){
	out.print("<div id='title'>");
	out.print("<h1>Admin Controls</h1>");
	out.print("</div>");
	out.print("<ul class='adminControls'>");	
	out.print("<li><a href='" + request.getContextPath() + "/CA2/createTourPage.jsp'>Tour Manager</a></li>");
	out.print("<li><a href='" + request.getContextPath() + "/CA2/createNewAdminPage.jsp'>Create New Admin User</a></li>");
	out.print("</ul>");
	
}
}
%>
</div><!-- End Main -->
<%@ include file="footer.jsp" %>
</body>
<script>
	const deleteButton = document.getElementById("deleteAcc")
	deleteButton.addEventListener("click", del)
	
	function del(){
		var confirm = window.confirm("Are you sure you want to delete your account?\nThis action is unreversable!")
		if(confirm){
			window.location.href = "deleteUser.jsp"
		}
	}
</script>
</html>