package nk.ssb.smashdb.service.auth.cookie;

import java.util.concurrent.TimeUnit;

import javax.inject.Provider;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;

import com.google.inject.Inject;

import nk.ssb.smashdb.core.AuthCookie;
import nk.ssb.smashdb.service.auth.stash.AuthCookieStash;
import nk.ssb.smashdb.service.exceptions.ExpiredCookieException;
import nk.ssb.smashdb.service.exceptions.InvalidCookieException;
import nk.ssb.smashdb.service.exceptions.MissingAuthException;

public class CookieAuthenticator {

  private static final int COOKIE_EXPIRY = (int) TimeUnit.DAYS.toMicros(30);

  private final AuthCookieCrypter authCookieCrypter;
  private final Provider<AuthCookieStash> authCookieStashProvider;

  @Inject
  public CookieAuthenticator(AuthCookieCrypter authCookieCrypter,
                             Provider<AuthCookieStash> authCookieStashProvider) {
    this.authCookieCrypter = authCookieCrypter;
    this.authCookieStashProvider = authCookieStashProvider;
  }

  public void authenticate(ContainerRequestContext containerRequestContext) {
    Cookie cookie = containerRequestContext.getCookies().get(CookieResponseGenerator.COOKIE_NAME);
    if (cookie == null) {
      throw new MissingAuthException();
    }
    AuthCookie authCookie;
    try {
      authCookie = authCookieCrypter.decrypt(cookie.getValue());
    } catch (Exception e) {
      throw new InvalidCookieException();
    }
    if (authCookie.getAuthenticatedAt() - System.currentTimeMillis() > COOKIE_EXPIRY) {
      throw new ExpiredCookieException();
    }
    authCookieStashProvider.get().set(authCookie);
  }
}
