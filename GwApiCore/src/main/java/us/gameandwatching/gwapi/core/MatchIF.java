package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

@GwStyle
@Value.Immutable
public interface MatchIF {
  int getPlayer1Id();
  int getPlayer2Id();

  MatchResult getMatchResult();
}
