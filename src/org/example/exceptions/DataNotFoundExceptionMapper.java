package org.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.example.models.ErrorMessage;

/**
 * 
 * This class registers {@link DataNotFoundException} to JAX-RS.
 * This allows us to return custom messages to Clients whenever DataNotFoundException occurs
 * 
 * @Provider annotation registers Exception to Jersey.
 * Note: Your class have to be scanned, with the configuration in your web.xml.
 * Example:
 * 		<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
		<init-param>
		     <param-name>jersey.config.server.provider.packages</param-name>
		     <param-value>org.example.resources,org.example.exceptions</param-value>
		</init-param> 
 *
 */


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage error = new ErrorMessage(404, exception.getMessage(), "http://localhost:8080");
		return Response.status(Status.NOT_FOUND)
				.entity(error)
				.build();
	}


}
