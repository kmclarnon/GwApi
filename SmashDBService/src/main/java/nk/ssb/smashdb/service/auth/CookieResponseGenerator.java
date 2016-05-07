package nk.ssb.smashdb.service.auth;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.inject.Inject;

import nk.ssb.smashdb.core.AuthCookie;

public class CookieResponseGenerator {

  private final AuthCookieCrypter authCookieCrypter;

  @Inject
  public CookieResponseGenerator(AuthCookieCrypter authCookieCrypter) {
    this.authCookieCrypter = authCookieCrypter;
  }

  public Response responseWithCookie(Response baseResponse, int userId) {
    ResponseBuilder responseBuilder = Response.fromResponse(baseResponse);
    responseBuilder.cookie(userAuthCookie(userId));
    return responseBuilder.build();
  }

  private NewCookie userAuthCookie(int userId) {
    String cookiePayload = authCookieCrypter.encrypt(AuthCookie.builder()
        .setUserId(userId)
        .setAuthenticatedAt(System.currentTimeMillis())
        .build());
    // TODO: update domain
    return new NewCookie("cookie-name", cookiePayload, null, "domain.com", null, -1, false);
  }
}
