package org.example.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.example.database.DataSource;
import org.example.exceptions.DataNotFoundException;
import org.example.models.Message;

public class MessageService {
	
	public Message save(Message message){
		if(null == message){
			return null;
		}
		int messageId = DataSource.messages.size()+1;
		message.setMessageId(messageId);
		DataSource.messages.put(messageId,message);
		return message;
	}
	
	
	public Message update(Message message){
		return DataSource.messages.put(message.getMessageId(), message);
	}
	
	public Message delete(int messageId){
		return DataSource.messages.remove(messageId);
	}
	
	/**
	 *  This method throws RuntimeException in case data is NOT found.
	 */
	public Message getMessage(int messageId){
		Message message =  DataSource.messages.get(messageId);
		if(null == message){
			throw new DataNotFoundException("No data found for messageId: "+messageId);
		}
		return message;
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(DataSource.messages.values());
	}
	
	
	/**
	 *  returns messages based on offset and limit provided in query parameters
	 */
	public List<Message> getMessages(int start, int limit){
		int size = DataSource.messages.values().size();
		if(start > size){
			return new ArrayList<Message>();
		}
		if(start+limit >= size){
			limit = size-start;
		}
		return new ArrayList<Message>(DataSource.messages.values()).subList(start, start+limit);
	}
	
	/**
	 * Returns messages by Year
	 */
	
	public List<Message> getMessages(int year){
		Calendar cal = Calendar.getInstance();
		List<Message> filteredMessages = new ArrayList<>();
		for(Message message : DataSource.messages.values()){
			cal.setTimeInMillis(message.getCreatedTime().getTime());
			if(cal.get(Calendar.YEAR) == year){
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}
}
