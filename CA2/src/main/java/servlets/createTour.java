package servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class createTour
 */
@WebServlet("/createTour")
public class createTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createTour() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		String tourName = request.getParameter("tourName");
		String category = request.getParameter("category");
		String tourDescription = request.getParameter("tourDescription");
		String tourDetailed = request.getParameter("tourDetailed");
		String tourCost = request.getParameter("tourCost");
		String tourSlots = request.getParameter("tourSlots");
		String tourPicture = request.getParameter("tourPicture");
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/jad_ca1?user=root&password=170304Cty&serverTimezone=UTC";
			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			// Step 5: Execute SQL Command
			String sqlStr = "INSERT INTO tour (tourName, tourDescription, tourDetailed, tourCost, tourSlots, tourType, tourPicture) VALUES (?, ?, ?, ?, ?,?, ?)";
			PreparedStatement pstmt = conn.prepareCall(sqlStr);
			pstmt.setString(1, tourName);
			pstmt.setString(2, tourDescription);
			pstmt.setString(3, tourDetailed);
			pstmt.setString(4, tourCost);
			pstmt.setString(5, tourSlots);
			pstmt.setString(6, category);
			pstmt.setString(7, tourPicture);
			//ResultSet rs = pstmt.executeQuery();
			// Step 6: Process Result
			int row = pstmt.executeUpdate();
			boolean success = false;
			if(row > 0) {
				success = true;
			}
			// Step 7: Close connection
			conn.close();
			if(success){
				response.sendRedirect("../TEst/CA1.5/createTourPage.jsp?code=success");
			}else{
				response.sendRedirect("createTourPage2?code=fail");
			}
		} catch (Exception e) {
				//System.out.println("Error :" + e);
				out.print("Error: " + e);
			}//End Catch
		}//End TRy
	}//End doPost


