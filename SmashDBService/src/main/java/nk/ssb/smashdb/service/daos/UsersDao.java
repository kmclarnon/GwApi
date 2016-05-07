package nk.ssb.smashdb.service.daos;

import java.util.List;
import java.util.Optional;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import nk.ssb.smashdb.core.users.User;
import nk.ssb.smashdb.core.users.UserEgg;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface UsersDao {
  @SqlQuery("SELECT * FROM users")
  List<User> getAllUsers();

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO users SET email=:email, password_hash=:password_hash, elo=:elo, created_at=:created_at")
  int insert(UserEgg userEgg);

  @SingleValueResult
  @SqlQuery("SELECT * FROM users WHERE email=:email")
  Optional<User> getUserByEmail(@Bind("email") String email);

  @SingleValueResult
  @SqlQuery("SELECT * FROM users WHERE username=:username")
  Optional<User> getUserByUsername(@Bind("username") String username);
}
