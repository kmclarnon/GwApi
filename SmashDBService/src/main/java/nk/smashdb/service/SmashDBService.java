package nk.smashdb.service;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SmashDBService extends Application<SmashDBConfiguration> {

  public static final String SERVICE_NAME = "SmashDB Service";

  public static void main(String[] args) throws Exception {
    new SmashDBService().run(args);
  }

  @Override
  public void initialize(Bootstrap<SmashDBConfiguration> bootstrap) {

    GuiceBundle<SmashDBConfiguration> guiceBundle = GuiceBundle.<SmashDBConfiguration>newBuilder()
        .addModule(new SmashDBServiceModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(SmashDBConfiguration.class)
        .build();

    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public String getName() {
    return SERVICE_NAME;
  }

  @Override
  public void run(SmashDBConfiguration configuration, Environment environment) throws Exception {

  }
}
