package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import toursDBAccess.TourDB;

/**
 * Servlet implementation class joinTour
 */
@WebServlet("/joinTour")
public class joinTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinTour() {
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
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		int tourID = Integer.parseInt(request.getParameter("tourID"));
		System.out.println(tourID);
		TourDB db = new TourDB();
		try {
			int rows = db.bookTour(email, tourID);
			if(rows > 0) {
				String url = "CA2/tours.jsp?code=success";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}else {
				String url = "CA2/tours.jsp?code=failed";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		}catch(Exception e) {
			System.out.println(e);
			String url = "CA2/tours.jsp?code=failed";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
