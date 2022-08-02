package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDBAccess.userDB;

/**
 * Servlet implementation class createUserServlet
 */
@WebServlet("/createUserServlet")
public class createUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createUserServlet() {
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
		userDB udb = new userDB();
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = "guest";
		String region = request.getParameter("region");
		try {
			int rows = udb.createUser(email, username, password, role, region);
			String url = "";
			if(rows > 0) {
				url="CA2/login.jsp?code=AccCreated";
			}else {
				url="CA2/register.jsp?errCode=fail";
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
