package nk.ssb.smashdb.service.auth;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.inject.Inject;

import nk.ssb.smashdb.core.AuthCookie;

public class CookieResponseGenerator {

  private static final String COOKIE_NAME = "gwauth";
  private static final String COOKIE_DOMAIN = "gameandwatch.af";
  private static final int COOKIE_AGE = (int) TimeUnit.DAYS.toMillis(365);

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

  public Response responseErasingCookie(Response baseResponse) {
    ResponseBuilder responseBuilder = Response.fromResponse(baseResponse);
    responseBuilder.cookie(new NewCookie(COOKIE_NAME, "", "/", COOKIE_DOMAIN, "", 0, false));
    return responseBuilder.build();
  }

  private NewCookie userAuthCookie(int userId) {
    String cookiePayload = authCookieCrypter.encrypt(AuthCookie.builder()
        .setUserId(userId)
        .setAuthenticatedAt(System.currentTimeMillis())
        .build());
    return new NewCookie(COOKIE_NAME, cookiePayload, "/", COOKIE_DOMAIN, "", COOKIE_AGE, false);
  }


}
