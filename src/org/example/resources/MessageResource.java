package org.example.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.example.models.Message;
import org.example.resources.beans.MessageFilterBean;
import org.example.services.MessageService;

/**
 * @Consumes : ContentType coming should match with it.
 * @Produces : Accept coming should match with it.
 *
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	
	private MessageService service = new MessageService();
	
	/**
	 *  Returns response as XML overriding default MediaType
	 *  Requires Model to have annotated with @XmlRootElement
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getAllMessagesAsXML(){
		return service.getAllMessages();
	}
	
	
	/**
	 *  Returns all messages response as JSON by default
	 *  This method also handles query parameters in mapped URI such as /messages?offset=10&limit=20
	 */
	//@GET
	@Deprecated
	public List<Message> getAllMessages(@QueryParam("year") int year, 
										@QueryParam("offset") int offset,
										@QueryParam("limit") int limit){
		if(year > 0){
			return service.getMessages(year);
		}
		else if(limit > 0){
			return service.getMessages(offset,limit);
		}
		return service.getAllMessages();
	}
	
	/**
	 *  Improved version with Bean implementation.
	 */
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filter){
		if(filter.getYear() > 0){
			return service.getMessages(filter.getYear());
		}
		else if(filter.getLimit() > 0){
			return service.getMessages(filter.getOffset(),filter.getLimit());
		}
		return service.getAllMessages();
	}

	/**
	 *  Returns single instance of Message response as JSON by default
	 *  @PathParam allows to map url patteren part of methods to input parameter.
	 *  Type conversion is taken care of automatically.
	 *  
	 *  @throws RunTimeException in case data is NOT found.
	 *  We have exception and error handling mechanism using ExceptionMapper which prevents bubbling down exception after JAX-RS.
	 *  @see DataNotFoundExceptionMapper
	 *  
	 */
	@GET
	@Path("/{messageId}") 
	public Message getMessage(@PathParam("messageId") int id, @Context UriInfo uriInfo){ 
		Message message = service.getMessage(id);
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path("/"+id)
				.build().toString();
		message.addLink(uri, "self");
		return  message;
	}
	
	/**
	 * 
	 * What if we want a control over sending headers and response?
	 * Return Response which uses Builder pattern.
	 * @throws URISyntaxException 
	 */
	@POST
	public Response createMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException{ 
/*		return Response.status(Status.CREATED)
					   .entity(service.save(message)).build();*/
		//sending status code 201 along with URI as header information
		//URI class expects everything after URL (http://localhost:8080) as URI location.
		Message newMessage = service.save(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(newMessage.getMessageId()+"").build();
		return Response.created(uri)
				   .entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}") 
	public Message updateMessage(@PathParam("messageId")int messageId, Message message){ 
		message.setMessageId(messageId);
		return  service.update(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") int id){ 
		service.delete(id);
	}
	
	/**
	 * 
	 * This method delegates the responsibility to Comments.
	 * 
	 */

	@Path("/{messageId}/comments")
	public CommentResource delegateToComments(){
		return new CommentResource();
	}
}
