package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.BadRequestException;

public class EmailUnavailableException extends BadRequestException {

  private final static String MESSAGE = "EMAIL_UNAVAILABLE";

  public EmailUnavailableException () {
    super(MESSAGE);
  }

}
