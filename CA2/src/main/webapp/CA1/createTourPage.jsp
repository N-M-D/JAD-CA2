<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<style>
#Main {
	margin: 15px;
}

#title {
	margin-left: auto;
	margin-right: auto;
}

.main {
	padding-bottom: 6.5em;
	position: relative;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main">
	<center>
		<h1>Create Tour</h1>
		<form method="post" action="../uploadServlet"
			enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>Name of Tour</td>
					<td><input type="text" name="tourName" size="50" /></td>
				</tr>
				<tr>
					<td>Type of Tour</td>
					<td><input type="text" name="tourType" size="50" /></td>
				</tr>
				<tr>
					<td>Breif Description of Tour</td>
					<td><input type="text" name="tourDescription" size="50" /></td>
				</tr>
				<tr>
					<td>Detailed Description of Tour</td>
					<td><input type="text" name="tourDetailed" size="50" /></td>
				</tr>
				<tr>
					<td>Cost of Tour</td>
					<td><input type="text" name="tourCost" size="50" /></td>
				</tr>
				<tr>
					<td>Tour Slots</td>
					<td><input type="text" name="tourSlots" size="50" /></td>
				</tr>
				<tr>
					<td>Photo of Tour</td>
					<td><input type="file" name="tourPicture" size="50" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Save"></td>
				</tr>
			</table>
		</form>
	</center>

	<center>
		<h1>Create Tour</h1>
		<form method="post" action="../updateServlet"
			enctype="multipart/form-data">
			<label for="tourName">Choose a tour:</label> <select
				id="selectTourName" name="selectTourName"></select>
			<%
			try {
				String tourNames[] = {};
				String tourTypes[] = {};
				String tourDescriptions[] = {};
				String tourCosts[] = {};
				int i = 0;
				// Step1: Load JDBC Driver
				Class.forName("com.mysql.jdbc.Driver"); //can be omitted for newer version of drivers

				// Step 2: Define Connection URL
				String connURL = "jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
				// Step 3: Establish connection to URL
				Connection conn = DriverManager.getConnection(connURL);
				// Step 4: Create Statement object
				Statement stmt = conn.createStatement();
				String sqlStr = "SELECT * FROM tour";
				PreparedStatement pstmt = conn.prepareStatement(sqlStr);
				// Step 5: Execute SQL Command      
				ResultSet rs = stmt.executeQuery(sqlStr);

				// Step 6: Process Result
				while (rs.next()) {

					ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(tourNames));
					arrayList.add(rs.getString("tourName"));
					tourNames = arrayList.toArray(tourNames);
				}
			%>
			<script>
			<%for (int c = 0; c < tourNames.length; c++) {%>
				console.log("<%=tourNames[c]%>")
				var newElement = document.createElement("option");
				newElement.innerHTML = "<%=tourNames[c]%>";
				newElement.value = "<%=tourNames[c]%>";
				document.getElementById("selectTourName").appendChild(
						newElement);
			<%}%>
				
			</script>

			<%
			// Step 7: Close connection
			conn.close();
			} catch (Exception e) {
			out.println("Error :" + e);
			}
			%>

			<table border="0">
				<tr>
					<td>Type of Tour</td>
					<td><input type="text" id= "tourType" name="tourType" size="50" /></td>
				</tr>
				<tr>
					<td>Description of Tour</td>
					<td><input type="text" id= "tourDescription"  name="tourDescription" size="50" /></td>
				</tr>
				<tr>
					<td>Detailed Description of Tour</td>
					<td><input type="text" name="tourDetailed" size="50" /></td>
				</tr>
				<tr>
					<td>Cost of Tour</td>
					<td><input type="text" id="tourCost" name="tourCost" size="50" /></td>
				</tr>
				<tr>
					<td>Tour Slots</td>
					<td><input type="text" name="tourSlots" size="50" /></td>
				</tr>
				<tr>
					<td>Photo of Tour</td>
					<td><input type="file" name="tourPicture" size="50" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Save" name="button"></td><td colspan="2"><input type="submit" value="delete" name ="button"></td>
				</tr>
			</table>
		</form>
	</center>
	<%@ include file="footer.jsp" %>
</div>
	
</body>
</html>