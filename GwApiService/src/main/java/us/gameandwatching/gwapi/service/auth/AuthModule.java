package us.gameandwatching.gwapi.service.auth;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import us.gameandwatching.gwapi.core.AuthCookie;
import us.gameandwatching.gwapi.service.auth.stash.AuthCookieStash;
import us.gameandwatching.gwapi.service.auth.stash.Stashed;

public class AuthModule extends AbstractModule {

  @Override
  protected void configure() {}

  @Provides
  @Stashed
  public AuthCookie provideStashedAuthCookie(AuthCookieStash authCookieStash) {
    return authCookieStash.get();
  }
}
