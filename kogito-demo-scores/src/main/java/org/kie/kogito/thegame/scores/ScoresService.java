package org.kie.kogito.thegame.scores;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/scores")
public class ScoresService {
	
	private long count = 0L;
	private Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public long count() {
        return count;
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean put(String multiplier) {
    	int number = random.nextInt(1000);
		if (number == 9) {
			count++;
			return true;
		}
    	return false;
    }

}