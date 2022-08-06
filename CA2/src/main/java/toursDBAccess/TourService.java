package toursDBAccess;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promoCodesDBAccess.PromoCode;
import promoCodesDBAccess.PromoCodeDB;

@Path("/TourService")
public class TourService {
	
	@GET
	@Path("/getAllTours")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTours() {
		ArrayList<Tour> tourList = new ArrayList<Tour>();
		try {
			TourDB db = new TourDB();
			tourList = db.getTours();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return Response
				.status(Response.Status.OK)
				.entity(tourList)
				.build();
	}
	
	@GET
	@Path("/getTourByID")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTour(@QueryParam("code") String code, @QueryParam("tourID") int tourID) {
		Tour tour = new Tour();
		PromoCode promoCode = new PromoCode();
		try {
			TourDB tourdb = new TourDB();
			tour = tourdb.getTour(tourID);
			if(code != null) {
				PromoCodeDB promoDB = new PromoCodeDB();
				promoCode = promoDB.claimPromoCode(code);
				if(promoCode.getDiscount() > 0) {
					float originalPrice = tour.getTourCost();
					float discountedPrice = (originalPrice - (originalPrice * promoCode.getDiscount()));
					tour.setTourCost(discountedPrice);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		if(tour.getTourID() > 0) {
			return Response
				.status(Response.Status.OK)
				.entity(tour)
				.build();
		}else {
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
	}
}
