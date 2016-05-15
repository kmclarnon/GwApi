package us.gameandwatching.gwapi.service.calc;

import com.google.inject.Singleton;

import us.gameandwatching.gwapi.core.EloPair;
import us.gameandwatching.gwapi.core.MatchResult;

@Singleton
public class EloCalculator {

  public EloPair updateEloRating(EloPair eloPair) {
    double player1startRating = eloPair.getPlayer1Rating();
    double player2StartRating = eloPair.getPlayer2Rating();

    double player1TransformedRating = computeTransformedRating(eloPair.getPlayer1Rating());
    double player2TransformedRating = computeTransformedRating(eloPair.getPlayer2Rating());

    double player1ExpectedRating = computeExpectedRating(player1TransformedRating, player2TransformedRating);
    double player2ExpectedRating = computeExpectedRating(player2TransformedRating, player1TransformedRating);

    double player1Score = getPlayer1Score(eloPair.getResult());
    double player2Score = getPlayer2Score(eloPair.getResult());

    return EloPair.builder()
        .setResult(eloPair.getResult())
        .setPlayer1Rating(computeUpdatedRating(player1startRating, getKFactor(player1startRating), player1ExpectedRating, player1Score))
        .setPlayer2Rating(computeUpdatedRating(player2StartRating, getKFactor(player2StartRating), player2ExpectedRating, player2Score))
        .build();
  }

  private int getKFactor(double rating) {
    // highly simplified k-factor calculations.
    // sometime in the future this should be replaced with effective game based k-factors
    // this will result in non-zero sum matches
    if (rating < 2100) {
      return 32;
    } else if(rating < 2400) {
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
