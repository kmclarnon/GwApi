package nk.ssb.smashdb.service.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

import com.google.inject.Injector;

import nk.ssb.smashdb.service.auth.cookie.CookieAuth;
import nk.ssb.smashdb.service.auth.cookie.CookieAuthenticator;

public class AuthFilter implements ContainerRequestFilter {

  @Context
  private ResourceInfo resourceInfo;

  private final CookieAuthenticator cookieAuthenticator;

  public AuthFilter(CaptureInjectorModule captureInjectorModule) {
    Injector injector = captureInjectorModule.getInjector();
    this.cookieAuthenticator = injector.getInstance(CookieAuthenticator.class);
  }

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    if (resourceInfo.getResourceClass().isAnnotationPresent(CookieAuth.class) ||
        resourceInfo.getResourceMethod().isAnnotationPresent(CookieAuth.class)) {
      cookieAuthenticator.authenticate(containerRequestContext);
    }
  }
}
