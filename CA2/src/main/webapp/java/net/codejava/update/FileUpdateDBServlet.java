package net.codejava.update;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/updateServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUpdateDBServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/jad_ca1";
	private String dbUser = "root";
	private String dbPass = "170304Cty";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String task = request.getParameter("button");
		String tourName = request.getParameter("selectTourName"); 


		Connection conn = null; // connection to the database
		String message = null;  // message will be sent back to client

		if(task.equals("delete")) {
			try {
				// connects to the database
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

				// constructs SQL statement
				String sqlStr = "DELETE FROM tour WHERE tourName=?";
				PreparedStatement pstmt = conn.prepareStatement(sqlStr);
				pstmt.setString(1, tourName);
				int row = pstmt.executeUpdate();
				if (row > 0) {
					message = "Tour deleted successfully";
				}

			} catch (SQLException ex) {
				message = "ERROR: " + ex.getMessage();
				ex.printStackTrace();
			} finally {
				if (conn != null) {
					// closes the database connection
					try {
						conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		}else {
			String tourType = request.getParameter("tourType");
			String tourDescription = request.getParameter("tourDescription");
			String tourCost = request.getParameter("tourCost");

			InputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("tourPicture");
			if ( request.getParameter("tourPicture") != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
				try {
					// connects to the database
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

					// constructs SQL statement
					String sql = "UPDATE tour SET tourName= ?, tourType= ?, tourDescription=?, tourCost=?, tourPicture=? WHERE tourName = ?";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, tourName);
					statement.setString(2, tourType);
					statement.setString(3, tourDescription);
					statement.setString(4, tourCost);

					if (inputStream != null) {
						// fetches input stream of the upload file for the blob column
						statement.setBlob(5, inputStream);
					}
					statement.setString(6, tourName); 
					// sends the statement to the database server
					int row = statement.executeUpdate();
					if (row > 0) {
						message = "File updated with image and saved into database";
					}
				} catch (SQLException ex) {
					message = "ERROR: " + ex.getMessage();
					ex.printStackTrace();
				} finally {
					if (conn != null) {
						// closes the database connection
						try {
							conn.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}else {  
				try {
					// connects to the database
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

					// constructs SQL statement
					String sql = "UPDATE tour SET tourName= ?, tourType= ?, tourDescription=?, tourCost=? WHERE tourName = ?";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, tourName);
					statement.setString(2, tourType);
					statement.setString(3, tourDescription);
					statement.setString(4, tourCost);

					statement.setString(5, tourName); 
					// sends the statement to the database server
					int row = statement.executeUpdate();
					if (row > 0) {
						message = "File updated without image and saved into database";
					}
				} catch (SQLException ex) {
					message = "ERROR: " + ex.getMessage();
					ex.printStackTrace();
				} finally {
					if (conn != null) {
						// closes the database connection
						try {
							conn.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}        		
		// sets the message in request scope
		request.setAttribute("Message", message);

		// forwards to the message page
		getServletContext().getRequestDispatcher("/CA1/Message.jsp").forward(request, response);
	}
}