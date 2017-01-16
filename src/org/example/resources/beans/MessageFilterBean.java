package org.example.resources.beans;

import javax.ws.rs.QueryParam;

/**
 * This class serves the purpose to check on number of params in method to filter messages
 *
 */
public class MessageFilterBean {
	
	private @QueryParam("year") int year;
	private @QueryParam("offset") int offset;
	private @QueryParam("limit") int limit;
	
	public MessageFilterBean(){}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
