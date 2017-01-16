package org.example.models;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	int commentId;
	int messageId;
	String comment;
	Timestamp createdTime = new Timestamp(System.currentTimeMillis());
	
	public Comment(){}

	public Comment(int commentId, int messageId, String comment) {
		super();
		this.commentId = commentId;
		this.messageId = messageId;
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
	
	
}
