package com.webservice.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.webservice.model.Tweet;

public class Tools {

	public Tweet convertStringToTweetClassObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Tweet tweet = null;
		// JSON from file to Object
		try {
			// JSON from String to Object
			tweet = mapper.readValue(json, Tweet.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tweet;
	}

}
