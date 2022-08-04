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
 * Servlet implementation class UpdateCustomer
 */
@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomer() {
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
		String button = request.getParameter("button");
		String email = request.getParameter("email");
		int points = Integer.parseInt(request.getParameter("points"));
		userDB db = new userDB();
		boolean success = false;
		String url = "/CA2/viewCustomerDetails.jsp";
		try {
			if(button.equals("edit")) {
				success = db.updatePoints(email, points);
				
			}else if(button.equals("delete")) {
				int rows = db.deleteUser(email);
				if(rows > 0) {
					url = "/CA2/viewCustomers.jsp";
					System.out.println("Delete user");
				}else {
					
				}
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
