package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.NotAuthorizedException;

public class InvalidPasswordException extends NotAuthorizedException {

  private final static String MESSAGE = "INVALID_PASSWORD";

  public InvalidPasswordException() {
    super(MESSAGE);
  }

}
