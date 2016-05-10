package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import nk.ssb.smashdb.core.ExceptionEntity;

public class ExpiredCookieException extends WebApplicationException {

  private final static String CODE = "INVALID_COOKIE";

  public ExpiredCookieException() {
    super(Response.status(Status.UNAUTHORIZED)
        .entity(ExceptionEntity.builder()
            .setCode(CODE)
            .build())
        .build());
  }
}
