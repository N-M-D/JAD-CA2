<!DOCTYPE html>
<html>
<!-- <body onload="myFunction()"> -->
<body>
<nav>
	<div id="logo">
		<h3>SPTravel</h3>
	</div>
	<ul>
		<li><a href="<%=request.getContextPath()%>/CA2/index.jsp">Home</a>
		<li><a href="<%=request.getContextPath()%>/CA2/tour.jsp">Tours</a></li>
		<% 
			String uid = (String) session.getAttribute("email");
			if(uid == null){
				out.print("<li><a href='" + request.getContextPath() + "/CA2/login.jsp'>Login</a></li>");
			}else{
				out.print("<li><a href='"+request.getContextPath() + "/CA2/user.jsp'>User</a></li>");
			}
		%>
	</ul>
</nav>
<!-- 	<script>
		function myFunction() {
			role = "admin"
			if(role == "admin"){
				document.getElementById("admin").innerHTML += "<a href='admin.jsp'>Admin</a>";
			}
		}
	</script> -->
</body>
</html>
