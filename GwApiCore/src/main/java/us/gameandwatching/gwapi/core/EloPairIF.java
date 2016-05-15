package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

@GwStyle
@Value.Immutable
public interface EloPairIF {
  double getPlayer1Rating();
  double getPlayer2Rating();

  MatchResult getResult();
}
