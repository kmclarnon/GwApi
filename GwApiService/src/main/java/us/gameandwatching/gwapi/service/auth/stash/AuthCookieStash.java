package us.gameandwatching.gwapi.service.auth.stash;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import us.gameandwatching.gwapi.core.AuthCookie;

@RequestScoped
public class AuthCookieStash {

  @Inject
  public AuthCookieStash() {}

  private Optional<AuthCookie> authCookie = Optional.empty();

  public void set(AuthCookie authCookie) {
    this.authCookie = Optional.of(authCookie);
  }

  public AuthCookie get() {
    return authCookie.get();
  }
}
