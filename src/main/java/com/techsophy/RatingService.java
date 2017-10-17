package com.techsophy;
 
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
				DBUtils.closeStatement(stmt);
				DBUtils.closeConnection(connection);					
			}
		return rowsAffected;
	}	
}