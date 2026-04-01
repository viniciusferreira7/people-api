package dev.vinicius.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailAlreadyTakenResponseExceptionMapper implements ExceptionMapper<EmailAlreadyTakenException> {
    @Override
    public Response toResponse(EmailAlreadyTakenException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }
}
