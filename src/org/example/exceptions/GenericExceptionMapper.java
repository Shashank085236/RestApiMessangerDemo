package org.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.example.models.ErrorMessage;

/**
 * This is generic exception handler which come into picture for all uncaught exceptions which are Not handled by any Mapper.
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage error = new ErrorMessage(500, "Internal Server error", "http://localhost:8080");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(error)
				.build();
	}
}
