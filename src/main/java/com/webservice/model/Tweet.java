package com.webservice.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class which represents one analyzed tweet 
 * @author Marcin
 *
 */

public class Tweet {

	@JsonProperty("candidate")
	private String candidate;
	@JsonProperty("time")
	private String time;
	@JsonProperty("sentiment")
	private String sentiment;

	
	public String getCandidate() {
		return candidate;
	}


	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getSentiment() {
		return sentiment;
	}


	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	@JsonCreator
	public Tweet( @JsonProperty("candidate")String candidate, @JsonProperty("time") String time, @JsonProperty("sentiment") String sentiment) {
		super();
		this.candidate = candidate;
		this.time = time;
		this.sentiment = sentiment;
	}
	
	
	
}
