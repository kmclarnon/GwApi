package nk.ssb.smashdb.service.daos;

import java.util.Optional;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import com.hubspot.rosetta.jdbi.BindWithRosetta;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import nk.ssb.smashdb.core.Secrets;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface SecretsDao {
  @SingleValueResult
  @SqlQuery("SELECT * FROM secrets LIMIT 1")
  Optional<Secrets> getSecrets();

  @SqlUpdate("INSERT INTO secrets SET cookie_key=:cookie_key")
  void insert(@BindWithRosetta Secrets secrets);
}
