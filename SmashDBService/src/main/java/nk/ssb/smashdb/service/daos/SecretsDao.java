package nk.ssb.smashdb.service.daos;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import nk.ssb.smashdb.core.Secrets;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface SecretsDao {
  @SqlQuery("SELECT * FROM secrets LIMIT 1")
  @SingleValueResult
  Secrets getSecrets();
}
