package nk.ssb.smashdb.service;

import org.skife.jdbi.v2.DBI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hubspot.rosetta.Rosetta;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nk.ssb.smashdb.service.auth.AuthModule;
import nk.ssb.smashdb.service.daos.LowerCaseWithUnderscoresModule;
import nk.ssb.smashdb.service.daos.OptionalContainerFactory;
import nk.ssb.smashdb.service.daos.SecretsDao;
import nk.ssb.smashdb.service.daos.UsersDao;

public class SmashDBServiceModule extends AbstractModule {

  @Override
  public void configure() {
    Rosetta.addModule(new LowerCaseWithUnderscoresModule());
    install(new AuthModule());
  }

  @Provides
  public DBI providesDbi(Environment environment, SmashDBConfiguration configuration) {
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
