package nk.ssb.smashdb.service.auth;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class CaptureInjectorModule extends AbstractModule {

  private Injector injector;

  @Override
  protected void configure() {
    binder().requestInjection(this);
  }

  @Inject
  public void setInjector(Injector injector) {
    this.injector = injector;
  }

  public Injector getInjector() {
    return injector;
  }
}
