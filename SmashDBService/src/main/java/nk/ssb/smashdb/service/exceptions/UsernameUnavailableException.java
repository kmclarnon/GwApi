package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.BadRequestException;

public class UsernameUnavailableException extends BadRequestException {

  private final static String MESSAGE = "USERNAME_UNAVAILABLE";

  public UsernameUnavailableException() {
    super(MESSAGE);
  }

}
