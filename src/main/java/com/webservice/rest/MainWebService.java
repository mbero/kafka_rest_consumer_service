package com.webservice.rest;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.webservice.kafka.KafkaConsumer;
 
@Path("/hello")
public class MainWebService {
	//http://localhost:8080/RESTfulKafkaConsumerService/rest/hello/start
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		if(msg.equals("start"))
		{
			KafkaConsumer consumer = new KafkaConsumer();
			consumer.start();
			return Response.status(200).entity("Kafka consumer started").build();
		}
		else
		{
			//Getting all saved / in memory tweets
			String output = "Jersey say : " + msg;
			return Response.status(200).entity(output).build();
		}
	}
 
}