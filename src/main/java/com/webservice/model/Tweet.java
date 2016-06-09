package com.webservice.model;

/**
 * Model class which represents one analyzed tweet 
 * @author Marcin
 *
 */

public class Tweet {


	private Integer id;
	private String candidate;
	private String time;
	private Boolean sentiment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Boolean getSentiment() {
		return sentiment;
	}
	public void setSentiment(Boolean sentiment) {
		this.sentiment = sentiment;
	}
	public Tweet( String candidate, String time, Boolean sentiment) {
		super();
		this.candidate = candidate;
		this.time = time;
		this.sentiment = sentiment;
	}
	
}
