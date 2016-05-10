package nk.ssb.smashdb.service.auth.stash;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import nk.ssb.smashdb.core.AuthCookie;

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
