package us.gameandwatching.gwapi.service;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import us.gameandwatching.gwapi.service.auth.AuthFilter;
import us.gameandwatching.gwapi.service.auth.CaptureInjectorModule;

public class GwApiService extends Application<GwApiConfiguration> {

  public static final String SERVICE_NAME = "GwApi Service";
  private CaptureInjectorModule captureInjectorModule = new CaptureInjectorModule();

  public static void main(String[] args) throws Exception {
    new GwApiService().run(args);
  }

  @Override
  public void initialize(Bootstrap<GwApiConfiguration> bootstrap) {
    GuiceBundle<GwApiConfiguration> guiceBundle = GuiceBundle.<GwApiConfiguration>newBuilder()
        .addModule(captureInjectorModule)
        .addModule(new GwApiServiceModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(GwApiConfiguration.class)
        .build();

    MigrationsBundle<GwApiConfiguration> migrationsBundle = new MigrationsBundle<GwApiConfiguration>() {
      @Override
      public DataSourceFactory getDataSourceFactory(GwApiConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    };

    bootstrap.addBundle(guiceBundle);
    bootstrap.addBundle(migrationsBundle);
  }

  @Override
  public String getName() {
    return SERVICE_NAME;
  }

  @Override
  public void run(GwApiConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new AuthFilter(captureInjectorModule));
  }
}
