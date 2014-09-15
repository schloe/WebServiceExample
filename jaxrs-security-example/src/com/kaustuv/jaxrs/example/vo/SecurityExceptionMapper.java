package com.kaustuv.jaxrs.example.vo;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.kaustuv.jaxrs.example.security.NotAuthorizedException;

@Provider
public class SecurityExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
  public Response toResponse(NotAuthorizedException exception) {
    Response.Status status;
    // This means that the client could not be authenticated. In this case the client may want to
    // send (new) credentials and we should return 401.
    status = Response.Status.UNAUTHORIZED;
    return Response.status(status).entity(exception.getLocalizedMessage()).build();
  }

}
