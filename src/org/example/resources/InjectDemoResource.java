package org.example.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/annotations")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	public String getDemoResource(@HeaderParam("sessionId") String sessionId, 
			@HeaderParam("Content-Type") String contentType,
			@CookieParam("name") String cookie
			){
		return "Header params: \nsessionId: "+sessionId+"\ncontentType: "+contentType+
			   "\n\nCookie params: \nname: "+cookie;
	}
	
	
	/**
	 *  Problems with above approach:
	 *  1. When number of parameters starts increasing, we have a lot of arguments.
	 *  2. name of cookie or header parameters should be known beforehand.
	 *  What if we want to see all header parameters or cookie parameters?
	 *  Annotation @Context to rescue.We can access UriInfo and HttpHeaders instance
	 */
	
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeader){
		StringBuilder params = new StringBuilder();
		params.append("\nAbsolutePath: "+uriInfo.getAbsolutePath());
		params.append("\nBaseUri: " +uriInfo.getBaseUri());
		params.append("\nPath parameters: "+uriInfo.getPathParameters());
		params.append("\nQuery parameters: "+uriInfo.getQueryParameters());
		params.append("\n\n HttpHeaders Params: \n");
		params.append("\n MediaType: "+httpHeader.getMediaType());
		params.append("\n Cookies: "+httpHeader.getCookies());
		params.append("\n RequestHeaders: "+httpHeader.getRequestHeaders());
		return params.toString();
	}
}
