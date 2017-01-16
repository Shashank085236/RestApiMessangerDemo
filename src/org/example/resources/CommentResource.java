package org.example.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.example.database.DataSource;
import org.example.models.Comment;

/**
 * 
 * Instead of using path like below, we can use concept of resource delegation.
 * 
 *
 */
//@Path("/messages/{messageId}/comments")
public class CommentResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComments(@PathParam("messageId") int messageId){
		return new ArrayList<>(DataSource.messageToComments.get(messageId).values());
	}
	
	/**
	 * Note: We can access even parent parameters here!
	 * For instance, messageId can be accessed.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
		Map<Integer,Comment> comments =  DataSource.messageToComments.get(messageId);
		int commentId_ = Integer.valueOf(messageId+""+commentId);
		return comments.get(commentId_);
	}
	
}
