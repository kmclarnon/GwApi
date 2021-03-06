package us.gameandwatching.gwapi.service.daos;

import java.util.List;
import java.util.Optional;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import com.hubspot.rosetta.jdbi.BindWithRosetta;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import us.gameandwatching.gwapi.core.users.User;
import us.gameandwatching.gwapi.core.users.UserEgg;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface UsersDao {
  @SqlQuery("SELECT * FROM users")
  List<User> getAllUsers();

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO users SET email=:email, username=:username, password_hash=:password_hash, password_salt=:password_salt, elo=:elo, created_at=:created_at")
  int insert(@BindWithRosetta UserEgg userEgg);

  @SingleValueResult
  @SqlQuery("SELECT * FROM users WHERE id=:id")
  Optional<User> getUserById(@Bind("id") int email);

  @SingleValueResult
  @SqlQuery("SELECT * FROM users WHERE email=:email")
  Optional<User> getUserByEmail(@Bind("email") String email);

  @SingleValueResult
  @SqlQuery("SELECT * FROM users WHERE username=:username")
  Optional<User> getUserByUsername(@Bind("username") String username);
}
