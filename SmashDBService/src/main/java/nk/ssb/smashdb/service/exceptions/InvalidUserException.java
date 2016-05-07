package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.BadRequestException;

public class InvalidUserException extends BadRequestException {

  private final static String MESSAGE = "INVALID_USER";

  public InvalidUserException() {
    super(MESSAGE);
  }

}
