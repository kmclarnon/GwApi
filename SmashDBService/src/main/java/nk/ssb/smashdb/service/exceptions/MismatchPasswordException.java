package nk.ssb.smashdb.service.exceptions;

import javax.ws.rs.BadRequestException;

public class MismatchPasswordException extends BadRequestException {

  private final static String MESSAGE = "MISMATCH_PASSWORD";

  public MismatchPasswordException() {
    super(MESSAGE);
  }

}
