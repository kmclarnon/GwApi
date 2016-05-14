package us.gameandwatching.gwapi.service.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import us.gameandwatching.gwapi.core.ExceptionEntity;

public class InvalidCookieException extends WebApplicationException {

  private final static String CODE = "INVALID_COOKIE";

  public InvalidCookieException() {
    super(Response.status(Status.UNAUTHORIZED)
        .entity(ExceptionEntity.builder()
            .setCode(CODE)
            .build())
        .build());
  }
}
