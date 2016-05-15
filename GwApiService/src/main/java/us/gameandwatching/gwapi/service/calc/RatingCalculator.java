package us.gameandwatching.gwapi.service.calc;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import us.gameandwatching.gwapi.core.EloPair;
import us.gameandwatching.gwapi.core.MatchWrap;

@Singleton
public class RatingCalculator {

  private final EloCalculator eloCalculator;

  @Inject
  public RatingCalculator(EloCalculator eloCalculator) {
    this.eloCalculator = eloCalculator;
  }

  public MatchWrap updateRatings(MatchWrap match) {
    // for now we just support elo for 2 player matches
    EloPair eloPair = eloCalculator.updateEloRating(match.toEloPair());
    return match
        .withPlayer1(match.getPlayer1().withElo(eloPair.getPlayer1Rating()))
        .withPlayer2(match.getPlayer2().withElo(eloPair.getPlayer2Rating()));
  }
}
