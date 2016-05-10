package nk.ssb.smashdb.service.auth;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import nk.ssb.smashdb.core.AuthCookie;
import nk.ssb.smashdb.service.auth.stash.AuthCookieStash;
import nk.ssb.smashdb.service.auth.stash.Stashed;

public class AuthModule extends AbstractModule {

  @Override
  protected void configure() {}

  @Provides
  @Stashed
  public AuthCookie provideStashedAuthCookie(AuthCookieStash authCookieStash) {
    return authCookieStash.get();
  }
}
