package org.example.database;

import java.util.HashMap;
import java.util.Map;

import org.example.models.Comment;
import org.example.models.Message;

public class DataSource {
	public static final Map<Integer,Message> messages = new HashMap<Integer, Message>();
	public static final Map<Integer,Comment> comments = new HashMap<Integer, Comment>();
	public static final Map<Integer,Map<Integer,Comment>> messageToComments = new HashMap<>();
	
	static
	{
		/**
		 * Initialize some dummy messages
		 */
		Message message1 = new Message(1, "message1", "Shashank");
		Message message2 = new Message(2, "message2", "Shashank");
		Message message3 = new Message(3, "message3", "Shashank");
		messages.put(1, message1);
		messages.put(2, message2);
		messages.put(3, message3);
		
		/**
		 *  Initializes some dummy Comments
		 */
		
		Comment comment11 = new Comment(11, 1, "comment1 on message1") ;
		Comment comment12 = new Comment(12, 1, "comment2 on message1") ;
		Comment comment21 = new Comment(21, 2, "comment1 on message2") ;
		Comment comment22 = new Comment(22, 2, "comment2 on message2") ;
		Comment comment31 = new Comment(31, 3, "comment1 on message3") ;
		comments.put(1, comment11);
		comments.put(2, comment12);
		comments.put(3, comment21);
		comments.put(4, comment22);
		comments.put(5, comment31);
		
		updateMessagesToComments();
		
	}

	/**
	 * This is util method which recreates whole entries in messagesToComments
	 */
	public static void updateMessagesToComments() {
		messageToComments.clear();
		for(Comment comment : comments.values()){
			Map<Integer,Comment> commentIdToComment = messageToComments.get(comment.getMessageId());
			commentIdToComment = (null == commentIdToComment) ? new HashMap<Integer,Comment>() : commentIdToComment;
			commentIdToComment.put(comment.getCommentId(),comment);
			messageToComments.put(comment.getMessageId(), commentIdToComment);
		}
	}
}
