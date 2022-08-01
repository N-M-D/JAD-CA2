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
		<li><a href="<%=request.getContextPath()%>/CA2/user.jsp">User</a></li>
		<!-- <li id = "admin"></li> -->
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
