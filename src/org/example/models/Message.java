package org.example.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
 * This jaxb annotation allows this class to be converted as XML or JSON
 */
@XmlRootElement
public class Message {
	private int messageId;
	private String message;
	private String author;
	private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
	
	/**
	 *  Models should have a no-arg constructor. to allow model to get
	 *  1. serialize and deserialize
	 *  2. XML and JSON conversion through APIs
	 */
	public Message(){}
	
	public Message(int messageId, String message, String author) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.author = author;
	}
	
	private List<Link> links = new ArrayList<Link>();
	
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public void addLink(String uri, String rel) {
		Link link = new Link();
		link.setURI(uri);
		link.setRel(rel);
		links.add(link);
	}

	public List<Link> getLinks(Link link) {
		return links;
	}
	
	
	
}
