package us.gameandwatching.gwapi.service;

import org.skife.jdbi.v2.DBI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hubspot.rosetta.Rosetta;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import us.gameandwatching.gwapi.service.auth.AuthModule;
import us.gameandwatching.gwapi.service.daos.LowerCaseWithUnderscoresModule;
import us.gameandwatching.gwapi.service.daos.OptionalContainerFactory;
import us.gameandwatching.gwapi.service.daos.SecretsDao;
import us.gameandwatching.gwapi.service.daos.UsersDao;

public class GwApiServiceModule extends AbstractModule {

  @Override
  public void configure() {
    Rosetta.addModule(new LowerCaseWithUnderscoresModule());
    install(new AuthModule());
  }

  @Provides
  public DBI providesDbi(Environment environment, GwApiConfiguration configuration) {
    DBIFactory dbiFactory = new DBIFactory();
    DBI dbi = dbiFactory.build(environment, configuration.getDataSourceFactory(), "mysql");
    dbi.registerContainerFactory(new OptionalContainerFactory());
    return dbi;
  }

  @Provides
  public UsersDao providesUsersDao(DBI dbi) {
    return dbi.onDemand(UsersDao.class);
  }

  @Provides
  public SecretsDao providesSecretsDao(DBI dbi) {
    return dbi.onDemand(SecretsDao.class);
  }
}
