package us.gameandwatching.gwapi.service.calc;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import us.gameandwatching.gwapi.core.EloPair;
import us.gameandwatching.gwapi.core.MatchResult;

@Singleton
public class EloCalculator {

  @Inject
  public EloCalculator() {

  }

  public EloPair updateEloRating(EloPair eloPair) {
    double i1 = eloPair.getPlayer1Rating();
    double i2 = eloPair.getPlayer2Rating();

    double r1 = computeTransformedRating(eloPair.getPlayer1Rating());
    double r2 = computeTransformedRating(eloPair.getPlayer2Rating());

    double e1 = computeExpectedRating(r1, r2);
    double e2 = computeExpectedRating(r1, r2);

    double s1 = getPlayer1Score(eloPair.getResult());
    double s2 = getPlayer2Score(eloPair.getResult());

    return EloPair.builder()
        .setResult(eloPair.getResult())
        .setPlayer1Rating(computeUpdatedRating(i1, getKFactor(i1), e1, s1))
        .setPlayer2Rating(computeUpdatedRating(i2, getKFactor(i1), e2, s2))
        .build();
  }

  private int getKFactor(double rating) {
    // highly simplified k-factor calculations.
    // sometime in the future this should be replaced with effective game based k-factors
    // this will result in non-zero sum matches
    if (rating < 2100) {
      return 32;
    } else if(rating > 2100 && rating < 2400) {
      return 24;
    } else {
      return 16;
    }
  }

  private double computeTransformedRating(double rating) {
    return Math.pow(10, rating / 400);
  }

  private double computeExpectedRating(double rating1, double rating2) {
    return rating1 / (rating1 + rating2);
  }

  private double computeUpdatedRating(double originalRating, int kFactor, double expectedRating, double score) {
    return originalRating + (double)kFactor * (score - expectedRating);
  }

  private double getPlayer1Score(MatchResult result) {
    if (result.getPlayer1Won()) {
      return 1;
    } else if (result.getPlayer2Won()) {
      return 0;
    } else {
      return 0.5;
    }
  }

  private double getPlayer2Score(MatchResult result) {
    if (result.getPlayer1Won()) {
      return 0;
    } else if (result.getPlayer2Won()) {
      return 1;
    } else {
      return 0.5;
    }
  }
}
