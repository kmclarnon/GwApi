package nk.ssb.smashdb.service.daos;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import nk.ssb.smashdb.core.Secrets;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface SecretsDao {
  @SqlQuery("SELECT * FROM secrets LIMIT 1")
  List<Secrets> getSecrets();
}
