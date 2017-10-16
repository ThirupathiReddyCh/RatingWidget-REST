package com.mkyong.rest;
 
import java.sql.Connection;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/ratingService")
public class RatingService {
 
	@GET
	@Path("/{rating}/{callSupportRating}/{recommendRating}/{feedback}")
	public Response getMsg(@PathParam("rating") String rating,@PathParam("callSupportRating") String callSupportRating,
			@PathParam("recommendRating") String recommendRating,
			@PathParam("feedback") String feedback) {
		String output = "Rating submitted succesfully";
		saveRatingDetails(rating,callSupportRating,recommendRating,feedback);
		return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET").build();
	}
	
	public int saveRatingDetails(String rating,
			String callSupportRating, String recommendRating,String feedback) {
		Connection connection = null;
		Statement stmt = null;
		int rowsAffected = 0;
			try {
				connection = DBUtils.getDBConnection();
				stmt = connection.createStatement();
				String query = "INSERT INTO  \"RatingInfo\" (\"rating\",\"callSupportRating\",\"recommendRating\",\"feedback\") "
						+ "VALUES ('"
						+ rating
						+ "','"
						+ callSupportRating
						+ "','"
						+ recommendRating
						+ "','"
						+ feedback + "')";
				if (stmt != null) {
					rowsAffected = stmt.executeUpdate(query);
				}
			} catch (Exception e) {
				System.out.println("In saveRatingDetails method catch block due to "+ e);
			} finally {
			}
		return rowsAffected;
	}
/*	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/saveUserRating", method = RequestMethod.GET)
	public @ResponseBody
	String saveUserRating(@RequestParam String rating,@RequestParam String ratingSuggestion,
			@RequestParam String feedback) {	
		ModelAndView model = new ModelAndView();
		String json = null;
		System.out.println("User Rating is: "+rating +" :"+ratingSuggestion+" :"+feedback);
		saveRatingDetails(rating,ratingSuggestion,feedback);
		model.addObject("message", "Rating submitted successfully");
		json = new Gson().toJson(model);
		return json;
	}*/
	
}