package us.gameandwatching.gwapi.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Provider;

import us.gameandwatching.gwapi.core.AuthCookie;
import us.gameandwatching.gwapi.service.auth.cookie.CookieAuth;
import us.gameandwatching.gwapi.service.auth.stash.Stashed;

@Path("users")
@CookieAuth
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

  private final Provider<AuthCookie> authCookie;

  @Inject
  public UsersResource(@Stashed Provider<AuthCookie> authCookie) {
    this.authCookie = authCookie;
  }

  @GET
  public int userId() {
    return authCookie.get().getUserId();
  }
}
