package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

import us.gameandwatching.gwapi.core.users.User;

@GwStyle
@Value.Immutable
public interface MatchWrapIF {
  User getPlayer1();
  User getPlayer2();

  int getPlayer1MatchesPlayed();
  int getPlayer2MatchesPlayed();

  MatchResult getMatchResult();

  @Value.Auxiliary
  default EloPair toEloPair() {
    return EloPair.builder()
        .setResult(getMatchResult())
        .setPlayer1Rating(getPlayer1().getElo())
        .setPlayer2Rating(getPlayer2().getElo())
        .build();
  }
}
