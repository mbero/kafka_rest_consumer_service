package com.webservice.rest;
 
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.webservice.database.TweetsDBService;
import com.webservice.kafka.KafkaConsumer;
import com.webservice.model.Tweet;
 
@Path("/hello")
public class MainWebService {
	//http://localhost:8080/RESTfulKafkaConsumerService/rest/hello/start
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		javax.ws.rs.core.Response response = null;
		if(msg.equals("start"))
		{
			KafkaConsumer consumer = new KafkaConsumer();
			consumer.start();
			response = Response.status(200).entity("Kafka consumer started").build();
		}
		else if(msg.equals("alltweets"))
		{
			//Getting all saved / in memory tweets
			TweetsDBService tweetsDBService = new TweetsDBService();
			List<Tweet> allTweetsFromDB = tweetsDBService.getAllObjectsFromDB();
			Tools tools = new Tools();
			String allTweetsJSON = tools.convertListOfTweetsToJSON(allTweetsFromDB);
			response = Response.status(200).entity(allTweetsJSON).build();
		}
		return response;
	}
 
}