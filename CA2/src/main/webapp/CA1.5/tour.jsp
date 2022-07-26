<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
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
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="Main">
		<div id="title">
			<h1>Tours</h1>
		</div>
	</div>

	<%	
	
try {
	byte[ ] imgData = null ;
	String tourNames[] = {};
	String tourTypes[] = {};
	String tourDescriptions[] = {};
	String tourCosts[] = {};
	String tourSlots[] = {};
	String tourDetails[] = {};
	String tourPictures[] = {};
	// Step1: Load JDBC Driver
	Class.forName("com.mysql.jdbc.Driver");
	// Step 2: Define Connection URL
	String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
	// Step 3: Establish connection to URL
	Connection conn = DriverManager.getConnection(connURL);
	// Step 4: Create Statement object
	Statement stmt = conn.createStatement();
	// Step 5: Execute SQL Command
	String sqlStr = "SELECT * FROM tour";
	PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	ResultSet rs = pstmt.executeQuery();
	// Step 6: Process Result
	while (rs.next()) {
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(tourNames));
		arrayList.add(rs.getString("tourName"));
		tourNames = arrayList.toArray(tourNames);
		
		ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(tourTypes));
		arrayList1.add(rs.getString("tourType"));
		tourTypes = arrayList1.toArray(tourTypes);
		
		ArrayList<String> arrayList2 = new ArrayList<String>(Arrays.asList(tourDescriptions));
		arrayList2.add(rs.getString("tourDescription"));
		tourDescriptions = arrayList2.toArray(tourDescriptions);
		
		ArrayList<String> arrayList3 = new ArrayList<String>(Arrays.asList(tourCosts));
		arrayList3.add(rs.getString("tourCost"));
		tourCosts = arrayList3.toArray(tourCosts);
		
		ArrayList<String> arrayList4 = new ArrayList<String>(Arrays.asList(tourSlots));
		arrayList4.add(rs.getString("tourSlots"));
		tourSlots = arrayList4.toArray(tourSlots);
		
		ArrayList<String> arrayList5 = new ArrayList<String>(Arrays.asList(tourDetails));
		arrayList5.add(rs.getString("tourDetailed"));
		tourDetails = arrayList5.toArray(tourDetails);
		Blob tourPicture = rs.getBlob("tourPicture");
		imgData = tourPicture.getBytes(1,(int)tourPicture.length());
		String encodedString = Base64.getEncoder().encodeToString(imgData);
				
				
		ArrayList<String> arrayList6 = new ArrayList<String>(Arrays.asList(tourPictures));
		arrayList6.add(encodedString);
		tourPictures = arrayList6.toArray(tourPictures);
		

		
		
	}
	%>
<script>
	
<%for (int c = 0; c < tourNames.length; c++) {%>

	 // creates a <table> element and a <tbody> element
	  var tbl = document.createElement("table");
	  var tblBody = document.createElement("tbody");


	  // put the <tbody> in the <table>
	  tbl.appendChild(tblBody);
	  // appends <table> into <body>
	  document.body.appendChild(tbl);
	  // sets the border attribute of tbl to '2'
	  tbl.setAttribute("border", "2");
	
	
	console.log("<%=tourNames[c]%>");
	
    var row = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell = document.createElement("td");
    var cellText = document.createTextNode("<%=tourNames[c]%> ");
    cell.appendChild(cellText);
    row.appendChild(cell);
  
    var row2 = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell2 = document.createElement("td");
    var cellText2 = document.createTextNode("Category of tour: <%=tourTypes[c]%> ");
    cell2.appendChild(cellText2);
    row2.appendChild(cell2);
    
    var row3 = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell3 = document.createElement("td");
    var cellText3 = document.createTextNode("Brief Description of tour: <%=tourDescriptions[c]%>");
    cell3.appendChild(cellText3);
    row3.appendChild(cell3);
    
    var row4 = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell4 = document.createElement("td");
    var cellText4 = document.createTextNode("Detailed Description of tour: <%=tourDetails[c]%>");
    cell4.appendChild(cellText4);
    row4.appendChild(cell4);
    
    var row5 = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell5 = document.createElement("td");
    var cellText5 = document.createTextNode("Slots available: <%=tourSlots[c]%>");
    cell5.appendChild(cellText5);
    row5.appendChild(cell5);
<%--     
    var row6 = document.createElement("tr");
    // Create a <td> element and a text node, make the text
    // node the contents of the <td>, and put the <td> at
    // the end of the table row
    var cell6 = document.createElement("td");
    var cellText6 = document.createTextNode("<%=tourDetails[c]%>");
    cell6.appendChild(cellText6);
    row6.appendChild(cell6); --%>

  // add the row to the end of the table body
  tblBody.appendChild(row);
  tblBody.appendChild(row2);
  tblBody.appendChild(row3);
  tblBody.appendChild(row4);
  tblBody.appendChild(row5);
/*   tblBody.appendChild(row6); */
	
<%}%>

</script>
<%
	// Step 7: Close connection
	conn.close();

} catch (Exception e) {
	out.println("Error :" + e);
}
%>
<%@ include file="footer.jsp" %>
</body>

</html>