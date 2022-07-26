package net.codejava.read;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/readServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileReadDBServlet extends HttpServlet {
     
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
        String tourName = request.getParameter("selectTourName");  
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "Select * from tour where tourName= ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tourName);

            // sends the statement to the database server
            ResultSet rs = statement.executeQuery(sql);
            request.setAttribute("tourName", rs.getString("tourName"));
            request.setAttribute("tourType", rs.getString("tourType"));
            request.setAttribute("tourDescription", rs.getString("tourDescription"));
            request.setAttribute("tourCost", rs.getString("tourCost"));
            request.setAttribute("tourPicture", rs.getBlob("tourPicture"));
            
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
            request.setAttribute("message", message);
            // forwards to the message page
            getServletContext().getRequestDispatcher("./createTourPage.jsp").forward(request, response);
        }
    }
}