package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toursDBAccess.TourDB;

import java.sql.*;

/**
 * Servlet implementation class updateTour
 */
@WebServlet("/updateTour")
public class updateTour extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTour() {
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
		
		int tourID = Integer.parseInt(request.getParameter("selectTourName"));
		
		TourDB db = new TourDB();
		if(button.equals("save")) {
			String tourName = request.getParameter("tourName");
			int category = Integer.parseInt(request.getParameter("category"));
			String tourDescription = request.getParameter("tourDescription");
			String tourDetailed = request.getParameter("tourDetailed");
			float tourCost = Float.parseFloat(request.getParameter("tourCost")) ;
			int tourSlots = Integer.parseInt(request.getParameter("tourSlots"));
			String tourPicture = request.getParameter("tourPicture");
			int rows = db.updateTour(tourID, tourName, tourDescription, tourDetailed, tourCost, tourSlots, category, tourPicture);
			if(rows == 0) {
				String url = "CA2/createTourPage.jsp?code=fail";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}else {
				String url = "CA2/createTourPage.jsp?code=success";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		}else if(button.equals("delete")) {
			boolean success = db.deleteTour(tourID);
			if(success) {
				String url = "CA2/createTourPage.jsp?code=success";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}else {
				String url = "CA2/createTourPage.jsp?code=fail";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		}else {
			System.out.println(button);
		}
		
	}
}