package nk.smashdb.service;

import org.skife.jdbi.v2.DBI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hubspot.rosetta.Rosetta;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nk.smashdb.service.daos.LowerCaseWithUnderscoresModule;

public class SmashDBServiceModule extends AbstractModule {

  @Override
  public void configure() {
    Rosetta.addModule(new LowerCaseWithUnderscoresModule());
  }

  @Provides
  public DBI providesDbi(Environment environment, SmashDBConfiguration configuration) {
    DBIFactory dbiFactory = new DBIFactory();
    return dbiFactory.build(environment, configuration.getDataSourceFactory(), "mysql");
  }
}
