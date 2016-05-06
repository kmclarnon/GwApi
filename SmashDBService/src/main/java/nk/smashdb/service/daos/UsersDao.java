package nk.smashdb.service.daos;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import nk.smashdb.core.User;
import nk.smashdb.core.UserEgg;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface UsersDao {
  @SqlQuery("SELECT * FROM users")
  List<User> getAllUsers();

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO users SET email=:email, password_hash=:password_hash, elo=:elo, created_at=:created_at")
  int insert(UserEgg userEgg);
}
