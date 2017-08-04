package com.fullLearn.endpoints.api;

import com.fullLearn.beans.Frequency;
import com.fullLearn.services.LearningStatsService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@Path("/api/v1/learn")
public class LearningStatsv1Endpoint {

    @GET
    @Path("/stats/report/user/{userid}")
    @Produces("application/json")
    public Response getUser(@PathParam("userid") String userId, @QueryParam("type") Frequency type, @QueryParam("limit") int limit) {

        Map<String, Object> userStats = new HashMap<>();
        Map<String, Object> userData = new HashMap<>();
        try{
            if (type == null)
                type = Frequency.WEEK;

            if (limit == 0) {
                limit = 12;
            }
            else if (limit > 20) {
                limit = 20;
            }
            else if(limit < 0){
                limit = 1;
            }

            LearningStatsService learningStatsService = new LearningStatsService();
            userData.put("stats",learningStatsService.getStatsByTypeForUserId(userId, type, limit));
            userStats.put("data",userData);
            userStats.put("response", true);
            return Response.status(Response.Status.OK).entity(userStats).build();

        }catch (Exception ex) {
            System.out.println(ex);

            userStats.put("msg", "Request Failed or Data not found or Check the URL");
            userStats.put("error", "Request Failed");
            userStats.put("response", false);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(userStats).build();
        }

    }
}