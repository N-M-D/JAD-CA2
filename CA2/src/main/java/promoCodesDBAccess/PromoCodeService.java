package promoCodesDBAccess;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/PromoCodeService")
public class PromoCodeService {
	
	@GET
	@Path("/getPromoCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPromoCode(String code) {
		PromoCode promoCode = new PromoCode();
		try {
			PromoCodeDB db = new PromoCodeDB();
			
			promoCode = db.claimPromoCode(code);
		}catch(Exception e) {
			System.out.println(e);
		}
		if(promoCode.getId() > 0) {
			return Response
					.status(Response.Status.OK)
					.entity(promoCode)
					.build();
		}else {
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(promoCode)
					.build();
		}
	}
	
//	@POST
//	@Path("/createPromoCode")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response createPromoCode(String code, float discount) {
//		int rows = 0;
//		try {
//			PromoCodeDB db = new PromoCodeDB();
//			rows = db.createPromoCode(code, discount);
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		if(rows > 0) {
//			return Response
//					.status(Response.Status.CREATED)
//					.build();
//		}else {
//			return Response
//					.status(Response.Status.CONFLICT)
//					.build();
//		}
//	}
}
