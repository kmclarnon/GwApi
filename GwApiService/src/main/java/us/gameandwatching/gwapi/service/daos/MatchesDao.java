package us.gameandwatching.gwapi.service.daos;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;

import us.gameandwatching.gwapi.core.Match;

@RegisterMapperFactory(RosettaMapperFactory.class)
public interface MatchesDao {

  @SqlQuery("SELECT * FROM matches")
  List<Match> getAllMatches();

  @SqlQuery("SELECT * FROM matches"
      + " WHERE player_1_id=:player_id"
      + " UNION"
      + " SELECT * FROM matches"
      + " WHERE player_2_id=:player_id")
  List<Match> getMatchesWithPlayer(@Bind("player_id") int playerId);

  @SqlQuery("SELECT COUNT(*) FROM"
      + " (SELECT 1 FROM matches"
      + "  WHERE player_1_id=:player_id"
      + "  UNION"
      + "  SELECT 1 FROM matches"
      + "  WHERE player_2_id=:player_id)")
  int getNumberOfMatchesPlayedByPlayer(@Bind("player_id") int playerId);

  @SqlQuery("SELECT * FROM matches"
      + " WHERE player_1_id=:player_1_id AND player_2_id=:player_2_id"
      + " UNION"
      + " SELECT * FROM matches"
      + " WHERE player_1_id=:player_2_id AND player_2_id=:player1_id")
  List<Match> getMatchesBetweenPlayers(@Bind("player_1_id") int playerId1,
                                       @Bind("player_2_id") int playerId2);
}
